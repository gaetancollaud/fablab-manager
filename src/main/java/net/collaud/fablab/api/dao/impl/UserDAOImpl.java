package net.collaud.fablab.api.dao.impl;

import java.util.List;
import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.UserEO;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gaetan
 */
@Repository
public class UserDAOImpl extends AbstractDAO<UserEO> implements UserDao {

	private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl() {
		super(UserEO.class);
	}

	@Override
	public UserEO getByLogin(String login) {
		Query query = sessionFactory.getCurrentSession().createQuery(UserEO.FIND_BY_IDS);
		query.setParameter(UserEO.PARAM_LOGIN, login);
		return (UserEO) query.uniqueResult();
	}

	@Override
	public List<UserEO> getAllUsers() {
		return findAll();
	}
}
