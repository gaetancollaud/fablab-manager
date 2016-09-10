package net.collaud.fablab.manager.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Component
public class SpringPropertiesUtils extends PropertySourcesPlaceholderConfigurer {

	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		super.setEnvironment(environment);
		this.environment = environment;
	}

	public Optional<String> getProperty(String name) {
		return Optional.ofNullable(environment.getProperty(name));
	}


}
