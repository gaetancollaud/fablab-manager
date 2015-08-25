package net.collaud.fablab.manager.dao.impl;

import java.io.Serializable;
import javax.persistence.EntityManager;
import net.collaud.fablab.manager.data.AbstractDataEO;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractDAO<T extends AbstractDataEO, ID extends Serializable> extends QueryDslJpaRepository<T, ID>{

	public AbstractDAO(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}


}
