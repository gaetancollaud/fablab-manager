package net.collaud.fablab.manager.boot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import net.collaud.fablab.manager.rest.HibernateAwareObjectMapper;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 *
 * @author Gaetan Collaud
 */
@Configuration
public class WebMVCConfiguration extends WebMvcAutoConfigurationAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new HibernateAwareObjectMapper());
		converter.setPrettyPrint(true);
		//converter.getObjectMapper().setDateFormat(DateFormat.getDateInstance());
		converter.setSupportedMediaTypes(supportedMediaTypes);
		converters.add(converter);
		super.configureMessageConverters(converters);
	}
}
