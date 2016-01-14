package net.collaud.fablab.manager.service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.ConfigurationRepository;
import net.collaud.fablab.manager.data.ConfigurationEO;
import net.collaud.fablab.manager.data.type.ConfigurationKey;
import net.collaud.fablab.manager.service.impl.AbstractServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Slf4j
public class ConfigurationServiceImpl extends AbstractServiceImpl implements ConfigurationService {
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Override
	public Map<ConfigurationKey, String> getAllConfiguration() {
		final Map<ConfigurationKey, String> map = configurationRepository.findAll().stream()
				.filter(c -> c.getKey() != null)
				.filter(c -> c.getValue() != null)
				.collect(Collectors.toMap(c -> c.getKey(), c -> c.getValue()));
		return Stream.of(ConfigurationKey.values())
				.collect(Collectors.toMap(k -> k, k -> Optional.ofNullable(map.get(k)).orElse(k.getDef())));
	}

	@Override
	public String getValue(ConfigurationKey key) {
		Optional<ConfigurationEO> opt = configurationRepository.getFromKey(key.name());
		return opt.map(ConfigurationEO::getValue)
				.orElse(key.getDef());
	}
	
}
