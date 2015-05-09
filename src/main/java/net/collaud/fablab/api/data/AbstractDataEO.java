package net.collaud.fablab.api.data;

import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <ID>
 */
@MappedSuperclass
@EqualsAndHashCode
abstract public class AbstractDataEO<ID> {
	abstract public ID getId();
}
