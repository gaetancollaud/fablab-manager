package net.collaud.fablab.api.service;

import java.util.Map;
import net.collaud.fablab.api.data.type.ConfigurationKey;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ConfigurationService {

	Map<ConfigurationKey, String> getAllConfiguration();
}
