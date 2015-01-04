package net.collaud.fablab.api.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.UserEO;
import org.hibernate.Query;
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
public class UserDAOImpl extends AbstractDAO<UserEO> implements UserDao {


	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {
		super(UserEO.class);
	}

	@Override
	public UserEO findOneByLogin(String login) {
		Query query = sessionFactory.getCurrentSession().createQuery(UserEO.FIND_BY_LOGIN);
		query.setParameter(UserEO.PARAM_LOGIN, login);
		return (UserEO) query.uniqueResult();
	}

	@Override
	public Iterable<UserEO> findAll() {
		return findAllEntities();
	}

	@Override
	public UserEO findOneByIdAndFetchRoles(Integer id) {
		Query query = sessionFactory.getCurrentSession().createQuery(UserEO.FIND_BY_ID_AND_FETCH_ROLES);
		query.setParameter(UserEO.PARAM_ID, id);
		return (UserEO) query.uniqueResult();
	}
}
