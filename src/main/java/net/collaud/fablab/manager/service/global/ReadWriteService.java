package net.collaud.fablab.manager.service.global;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <T>
 */
public interface ReadWriteService<T> extends ReadService<T> {

	T save(T entity);

	void remove(Integer id);

//	void softRemove(Integer id);
}
