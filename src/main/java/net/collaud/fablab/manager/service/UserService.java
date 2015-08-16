package net.collaud.fablab.manager.service;

import java.util.Optional;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService extends ReadWriteService<UserEO>{

	Optional<UserEO> findByLogin(String login);
	
	void signup(UserEO user, String recaptcha);
	
	void forgotPassword(String email, String recaptchaResponse);

	public void updateMailingList();
}
