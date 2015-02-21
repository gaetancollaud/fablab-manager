package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService extends ReadWriteService<UserEO>{

	UserEO findByLogin(String login);
	
	void signup(UserEO user, String recaptcha);
}
