package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.result.ConnectedUser;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface SecurityService {

	ConnectedUser getConnectedUser();
	
	UserEO getCurrentUser();
	
	Integer getCurrentUserId();
	
	Boolean isAuthenticated();
	
	LoginResult login(String login, String password);
	
	void logout();
	
}
