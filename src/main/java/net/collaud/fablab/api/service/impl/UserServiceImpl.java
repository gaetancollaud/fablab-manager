package net.collaud.fablab.api.service.impl;

import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserEO findByLogin(String login) {
		if (login == null || login.isEmpty()) {
			return null;
		}
		return userDao.findOneByLogin(login);
	}

	@Override
	public Iterable<UserEO> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public UserEO findById(Integer id) {
		return userDao.findOneById(id);
	}

	@Override
	public UserEO save(UserEO user) {
		return userDao.save(user);
	}

}
