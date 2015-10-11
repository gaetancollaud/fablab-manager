package net.collaud.fablab.manager.service;

import java.util.Optional;
import net.collaud.fablab.manager.audit.Audit;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService extends ReadWriteService<UserEO> {

    Optional<UserEO> findByLogin(String login);

	@Audit(object = AuditObject.USER, action = AuditAction.SIGNUP)
    void signup(UserEO user, String recaptcha);

    void forgotPassword(String email, String recaptchaResponse);

    void updateMailingList();

    boolean canUse(Integer machineTypeId, Integer userId);

    boolean hasRole(Integer userId, String role);

    Double balance(Integer userId);
	
	@Override
	@Audit(object = AuditObject.USER, action = AuditAction.SAVE)
	public UserEO save(UserEO entity);
	
	@Override
	@Audit(object = AuditObject.USER, action = AuditAction.DELETE)
	public void remove(Integer id);


	Optional<UserEO> findByRFID(String rfid);
}
