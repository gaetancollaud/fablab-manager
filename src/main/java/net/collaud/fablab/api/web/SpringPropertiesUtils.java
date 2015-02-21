package net.collaud.fablab.api.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class SpringPropertiesUtils extends PropertyPlaceholderConfigurer {
	
	private Map<String, String> propertiesMap;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory,
			Properties props) throws BeansException {
		super.processProperties(beanFactory, props);
		propertiesMap = new HashMap<>();
		props.keySet().stream().map(key -> key.toString()).forEach(keyStr
				-> propertiesMap.put(keyStr, props.getProperty(keyStr))
		);
	}

	public Optional<String> getProperty(String name) {
		return Optional.ofNullable(propertiesMap.get(name));
	}

	public Map<String, Optional<String>> getProperties(Map.Entry<String, String>... keys) {
		Map<String, Optional<String>> ret = new HashMap<>();
		for (Map.Entry<String, String> e : keys) {
			ret.put(e.getValue(), getProperty(e.getKey()));
		}
		return ret;
	}
}
