package net.collaud.fablab.api.data;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <ID>
 */
@MappedSuperclass
abstract public class AbstractDataEO<ID> {
	abstract ID getId();
	abstract void setId(ID id);
}
