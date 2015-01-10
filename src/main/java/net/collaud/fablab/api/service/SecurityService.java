package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.LoginResult;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface SecurityService {

	UserEO getCurrentUser();
	
	Integer getCurrentUserId();
	
	Boolean isAuthenticated();
	
	LoginResult login(String login, String password);
	
	void logout();
	
}
