package net.collaud.fablab.api.web;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Controller
public class ConstansController {

	@Autowired
	private SpringPropertiesUtil properties;

	@RequestMapping("/constants.js")
	public @ResponseBody
	String constants(Model model) {
		String rootUrl = "/fablab-api";
		
		Map<String, String> csts = new HashMap<>();
		
		//from fablab-config.properties
		csts.put("rootUrl", properties.getProperty(rootUrl).orElse("/"));
		csts.put("GOOGLE_API", properties.getProperty("google.api").orElse(""));

		StringBuilder sb = new StringBuilder("var App = {};\n");
		addConstants(sb, "App.Constants", csts);
		addConstants(sb, "App.API", getRestConstants(rootUrl));

		return sb.toString();
	}

	private void addConstants(StringBuilder sb, String name, Map<String, String> csts) {
		sb.append(name).append(" = {};\n");
		csts.entrySet().forEach(e -> sb.append(name).append(".").append(e.getKey()).append("='").append(e.getValue()).append("';\n"));
	}

	private Map<String, String> getRestConstants(String rootUrl) {
		Map<String, String> api = new HashMap<>();
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
							rootUrl+"/api"+value[0]
					);
				}
			}
		}
		return api;
	}
}
