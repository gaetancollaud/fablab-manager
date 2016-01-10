package net.collaud.fablab.manager.service;

import java.util.Optional;
import net.collaud.fablab.manager.audit.Audit;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.data.type.LoginResult;
import net.collaud.fablab.manager.rest.v1.result.ConnectedUser;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface SecurityService {

	ConnectedUser getConnectedUser();
	
	Optional<UserEO> getCurrentUser();
	
	Integer getCurrentUserId();
	
	Boolean isAuthenticated();
	
	@Audit(object = AuditObject.SECURITY, action = AuditAction.LOGIN)
	LoginResult login(String login, String password);
	
	@Audit(object = AuditObject.SECURITY, action = AuditAction.LOGOUT)
	void logout();
	
	boolean hasRole(String role);
	
	void checkRole(String role);
	
}
