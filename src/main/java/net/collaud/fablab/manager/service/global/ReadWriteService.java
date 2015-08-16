package net.collaud.fablab.manager.service.global;

import net.collaud.fablab.manager.service.global.ReadService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <T>
 */
public interface ReadWriteService<T> extends ReadService<T>{
	T save(T entity);
	void remove(Integer id);
}
