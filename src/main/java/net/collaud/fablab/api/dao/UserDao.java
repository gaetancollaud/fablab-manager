package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.UserEO;
/**
 *
 * @author gaetan
 */
public interface UserDao{

	UserEO findOneById(Integer id);
	UserEO findOneByLogin(String login);
	Iterable<UserEO> findAll();
}
