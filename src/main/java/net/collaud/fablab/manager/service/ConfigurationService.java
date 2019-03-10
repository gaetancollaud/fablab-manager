package net.collaud.fablab.manager.service;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.data.type.ConfigurationKey;
import net.collaud.fablab.manager.service.impl.AbstractServiceImpl;
import net.collaud.fablab.manager.web.SpringPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Slf4j
public class ConfigurationService extends AbstractServiceImpl {

	@Autowired
	private SpringPropertiesUtils applicationProperties;

	@Value("${google.recaptcha.site:bla}")
	private String recaptcha;

	public Map<ConfigurationKey, String> getAllPublicConfiguration() {
		return Arrays.stream(ConfigurationKey.values())
				.filter(ConfigurationKey::isPublic)
				.collect(Collectors.toMap(
						k -> k,
						k -> applicationProperties.getProperty(k.getKey()).orElse(k.getDef())));
	}

	public String getValue(ConfigurationKey key) {
		return applicationProperties.getProperty(key.getKey()).orElse(key.getDef());
	}

}
