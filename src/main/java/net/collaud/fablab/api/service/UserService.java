package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService {

	UserEO findByLogin(String login);

	Iterable<UserEO> getAllUsers();

}
