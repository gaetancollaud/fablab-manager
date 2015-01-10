package net.collaud.fablab.api.dao.impl;

import java.util.List;
import net.collaud.fablab.api.dao.RoleDao;
import net.collaud.fablab.api.data.RoleEO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
@Transactional
public class RoleDaoImpl extends AbstractDAO<RoleEO> implements RoleDao {

	public RoleDaoImpl() {
		super(RoleEO.class);
	}

	@Override
	public List<RoleEO> getAllRoles() {
		return findAllEntities();
	}
}
