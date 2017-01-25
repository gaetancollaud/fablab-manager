package net.collaud.fablab.manager.web;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
