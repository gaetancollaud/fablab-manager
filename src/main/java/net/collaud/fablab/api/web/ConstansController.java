package net.collaud.fablab.api.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Controller
@Slf4j
public class ConstansController {

	@Autowired
	private SpringPropertiesUtils propertyUtils;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value="/constants.js", produces = "application/javascript; charset=utf-8")
	public @ResponseBody
	String constants() {
		String rootUrl = propertyUtils.getProperty("url.root").orElse("/");

		Map<String, Object> csts = new HashMap<>();

		//from fablab-config.properties
		csts.put("rootUrl", rootUrl);
		csts.put("GOOGLE_API", propertyUtils.getProperty("google.api").orElse(""));
		csts.put("RECAPTCHA_SITE_KEY", propertyUtils.getProperty("google.recaptcha.site").orElse(""));

		StringBuilder sb = new StringBuilder("var App = {};\n");
		addConstants(sb, "App.Constants", csts);
		addConstants(sb, "App.API", getRestConstants(rootUrl));
		addConstant(sb, "App.connectedUser", securityService.getConnectedUser());

		return sb.toString();
	}

	private void addConstant(StringBuilder sb, String name, Object value) {
		sb.append(name).append("=").append(stringify(value)).append(";\n");
	}

	private void addConstants(StringBuilder sb, String name, Map<String, Object> csts) {
		sb.append(name).append(" = {};\n");
		csts.entrySet().forEach(e
				-> sb.append(name).append(".").append(e.getKey()).append("=")
				.append(stringify(e.getValue())).append(";\n"));
	}

	private Map<String, Object> getRestConstants(String rootUrl) {
		Map<String, Object> api = new HashMap<>();
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(JavascriptAPIConstant.class));
		for (BeanDefinition bd : scanner.findCandidateComponents("net.collaud.fablab.api.rest")) {
			ScannedGenericBeanDefinition sgbd = (ScannedGenericBeanDefinition) bd;
			final Optional<Map<String, Object>> javascript = sgbd.getMetadata().getAnnotationTypes().stream()
					.filter(a -> a.equals(JavascriptAPIConstant.class.getName()))
					.map(a -> sgbd.getMetadata().getAnnotationAttributes(a))
					.findFirst();
			final Optional<Map<String, Object>> rest = sgbd.getMetadata().getAnnotationTypes().stream()
					.filter(a -> a.equals(RequestMapping.class.getName()))
					.map(a -> sgbd.getMetadata().getAnnotationAttributes(a))
					.findFirst();

			if (rest.isPresent() && javascript.isPresent()) {
				String[] value = (String[]) rest.get().get("value");
				if (value.length > 0) {
					api.put(
							javascript.get().get("value").toString(),
							rootUrl + "/api" + value[0]
					);
				}
			}
		}
		return api;
	}

	private String stringify(Object o) {
		try {
			final ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException ex) {
			log.error("Cannot parse object " + o, ex);
		}
		return "";
	}
}
