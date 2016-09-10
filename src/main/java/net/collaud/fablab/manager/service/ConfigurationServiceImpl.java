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
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Slf4j
public class ConfigurationServiceImpl extends AbstractServiceImpl implements ConfigurationService {
	
//	@Autowired
//	private ConfigurationRepository configurationRepository;
//
	@Autowired
	private SpringPropertiesUtils applicationProperties;



	@Value("${google.recaptcha.site:bla}")
	private String recaptcha;

	
	@Override
	public Map<ConfigurationKey, String> getAllConfiguration() {
		return Arrays.stream(ConfigurationKey.values())
				.collect(Collectors.toMap(
						k -> k,
						k -> applicationProperties.getProperty(k.getKey()).orElse(k.getDef())));
//		final Map<ConfigurationKey, String> map = configurationRepository.findAll().stream()
//				.filter(c -> c.getKey() != null)
//				.filter(c -> c.getValue() != null)
//				.collect(Collectors.toMap(c -> c.getKey(), c -> c.getValue()));
//		return Stream.of(ConfigurationKey.values())
//				.collect(Collectors.toMap(k -> k, k -> Optional.ofNullable(map.get(k)).orElse(k.getDef())));
	}

	@Override
	public String getValue(ConfigurationKey key) {
		return applicationProperties.getProperty(key.getKey()).orElse(key.getDef());
//
//		SpringPropertiesUtils
//		Optional<ConfigurationEO> opt = configurationRepository.getFromKey(key.name());
//		return opt.map(ConfigurationEO::getValue)
//				.orElse(key.getDef());
	}
	
}
