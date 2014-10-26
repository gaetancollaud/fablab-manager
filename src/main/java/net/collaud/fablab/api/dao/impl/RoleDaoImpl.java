package net.collaud.fablab.api.dao.impl;

import java.util.List;
import net.collaud.fablab.api.dao.RoleDao;
import net.collaud.fablab.api.data.RoleEO;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gaetan
 */
@Repository
@Transactional
public class RoleDaoImpl extends AbstractDAO<RoleEO> implements RoleDao {

	private static final Logger LOG = Logger.getLogger(RoleDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public RoleDaoImpl() {
		super(RoleEO.class);
	}

	@Override
	public List<RoleEO> getAllRoles() {
		return findAllEntities();
	}
}
