package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService {

	UserEO findByLogin(String login);
	
	UserEO findById(Integer id);

	Iterable<UserEO> getAllUsers();
	
	UserEO save(UserEO user);

}
