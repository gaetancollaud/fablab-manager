package net.collaud.fablab.api.service.impl;

import java.util.List;
import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.exceptions.FablabWrongParameterException;
import net.collaud.fablab.api.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gaetan
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl implements UserService {
	private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserEO findByLogin(String login) throws FablabException{
		if(login==null || login.isEmpty()){
			throw new FablabWrongParameterException("login cannot be null or empty");
		}
		return userDao.findOneByLogin(login);
	}

	@Override
	public Iterable<UserEO> getAllUsers() {
		return userDao.findAll();
	}

}
