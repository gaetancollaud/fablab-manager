package net.collaud.fablab.api.service;

import net.collaud.fablab.api.service.impl.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.dao.ConfigurationRepository;
import net.collaud.fablab.api.data.type.ConfigurationKey;
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
				.collect(Collectors.toMap(c -> c.getKey(), c -> c.getValue()));
		return Stream.of(ConfigurationKey.values())
				.collect(Collectors.toMap(k -> k, k -> map.getOrDefault(k, k.getDef())));
	}

}
