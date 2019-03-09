package net.collaud.fablab.manager.service;

import java.util.Optional;
import net.collaud.fablab.manager.audit.Audit;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.data.type.ChangePasswordResult;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService extends ReadWriteService<UserEO>{

	Optional<UserEO> findByLogin(String login);

	void signup(UserEO user, String recaptcha);
	
	void forgotPassword(String email, String recaptchaResponse);

	UserEO acceptPasswordChange(UserEO user);

	void updateMailingList();

	@Override
	void remove(Long id);

	@Override
	UserEO save(UserEO entity);

	Optional<UserEO> findByRFID(String rfid);

	ChangePasswordResult changePassword(String old, String newPass, String repeatPass);
}
