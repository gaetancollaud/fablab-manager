package net.collaud.fablab.manager.service;

import java.util.Optional;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.LoginResult;
import net.collaud.fablab.manager.rest.v1.result.ConnectedUser;
import net.collaud.fablab.manager.security.Roles;

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
