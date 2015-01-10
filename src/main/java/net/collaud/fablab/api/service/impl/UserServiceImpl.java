package net.collaud.fablab.api.service.impl;

import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.service.UserService;
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
		if (user.getUserId() > 0) {
			UserEO old = userDao.findOneById(user.getUserId());
			old.setFirstname(user.getFirstname());
			old.setLastname(user.getLastname());
			old.setEmail(user.getEmail());
			return userDao.save(old);
		} else {
			//FIXME references
			return userDao.save(user);
		}
	}

}
