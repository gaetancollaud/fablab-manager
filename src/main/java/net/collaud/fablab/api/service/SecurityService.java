package net.collaud.fablab.api.service;

import java.util.Optional;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.result.ConnectedUser;
import net.collaud.fablab.api.security.Roles;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface SecurityService {

	ConnectedUser getConnectedUser();
	
	Optional<UserEO> getCurrentUser();
	
	Integer getCurrentUserId();
	
	Boolean isAuthenticated();
	
	LoginResult login(String login, String password);
	
	void logout();
	
	boolean hasRoles(String roles);
	
	void checkRoles(String roles);
	
}
