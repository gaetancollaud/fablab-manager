package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.UserEO;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserDao{

	UserEO findOneByIdAndFetchRoles(Integer id);
	UserEO findOneByLogin(String login);
	Iterable<UserEO> findAll();
	UserEO findOneById(Integer id);

	public UserEO save(UserEO user);
}
