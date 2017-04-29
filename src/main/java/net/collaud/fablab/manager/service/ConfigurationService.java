package net.collaud.fablab.manager.service;

import java.util.Map;
import net.collaud.fablab.manager.data.type.ConfigurationKey;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ConfigurationService {

	Map<ConfigurationKey, String> getAllPublicConfiguration();

	String getValue(ConfigurationKey key);
}
