package net.collaud.fablab.api.audit;

import java.util.Date;
import net.collaud.fablab.api.data.AuditEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.AuditAction;
import net.collaud.fablab.api.data.type.AuditObject;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.AuditService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
abstract public class AuditUtils {
	
	public static void addAudit(AuditService service, UserEO user, AuditObject what, AuditAction action, boolean success, String content, String detail) throws FablabException {
		AuditEO audit = new AuditEO();
		audit.setWho(user);
		audit.setWhen(new Date());
		audit.setContent(content);
		audit.setSuccess(success);
		audit.setObject(what);
		audit.setAction(action);
		audit.setDetail(detail);
		service.addEntry(audit);
	}

	public static void addAudit(AuditService service, UserEO user, AuditObject what, AuditAction action, boolean success, String content) throws FablabException {
		addAudit(service, user, what, action, success, content, null);
	}

	public static void addAudit(AuditService service, AuditObject what, AuditAction action, boolean success, String content) throws FablabException {
		addAudit(service, null, what, action, success, content, null);
	}
}
