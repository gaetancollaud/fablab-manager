package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.ConfigurationEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ConfigurationService extends ReadWriteService<ConfigurationEO> {

	 ConfigurationEO findByKey(String key);
}
