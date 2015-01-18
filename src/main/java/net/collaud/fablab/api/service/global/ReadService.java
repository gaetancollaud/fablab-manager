package net.collaud.fablab.api.service.global;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <T>
 */
public interface ReadService<T> {
	List<T> findAll();
	Optional<T> getById(Integer id);
}
