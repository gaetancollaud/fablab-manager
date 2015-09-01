package net.collaud.fablab.manager.audit;

import java.lang.reflect.Method;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.data.AbstractDataEO;
import net.collaud.fablab.manager.data.AuditEO;
import net.collaud.fablab.manager.data.PaymentEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.data.type.LoginResult;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.service.AuditService;
import net.collaud.fablab.manager.service.SecurityService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Aspect
@Slf4j
@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuditAspect {
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private SecurityService securityService;

//	@Around("@within(net.collaud.fablab.manager.audit.Audit) || @annotation(net.collaud.fablab.manager.audit.Audit)")
//	@Around("@annotation(net.collaud.fablab.manager.audit.Audit)")
	@Around("execution(* net.collaud.fablab.manager.service.*.*(..))")
	public Object serviceAction(ProceedingJoinPoint pjp) throws Throwable {
		final Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		if (method.isAnnotationPresent(Audit.class)) {
			// perform the monitoring actions
			return aroudAudit(pjp, method);
		}
		return pjp.proceed();
	}
	
	protected Object aroudAudit(ProceedingJoinPoint pjp, Method method) throws Throwable {
		log.info("Intercepted method " + method.getName());
		Audit ann = method.getAnnotation(Audit.class);
		try {
			Object result = pjp.proceed();
			try {
				Object object = getObjectOutOfResultAndParameters(ann, result, pjp.getArgs());
				Integer id = getIdOfObject(object);
				addEntry(ann.action(), ann.object(), id, true, getReadableMessage(ann.object(), ann.action(), object, pjp.getArgs()), null);
			} catch (Exception ex) {
				log.error("Cannot add login for themod " + method.getName(), ex);
			}
			return result;
		} catch (Exception ex) {
			Object entity = getObjectOutOfResultAndParameters(ann, null, pjp.getArgs());
			Integer id = getIdOfObject(entity);
			addEntry(ann.action(), ann.object(), id, true, "Error while " + ann.action() + " " + ann.object() + " with id  " + id, ex.getMessage());
			throw ex;
		}
	}
	
	private void addEntry(AuditAction action, AuditObject object, Integer objectId, boolean success, String content, String detail) throws FablabException {
		if (detail != null && detail.isEmpty()) {
			detail = null;
		}
		auditService.addEntry(new AuditEO(securityService.getCurrentUser().orElse(null), action, object, objectId, new Date(), success, content, detail));
	}
	
	private Object getObjectOutOfResultAndParameters(Audit ann, Object result, Object[] parameters) {
		if (ann.action().equals(AuditAction.INSERT)
				|| ann.action().equals(AuditAction.UPDATE)) {
			if (result instanceof AbstractDataEO) {
				return (AbstractDataEO) result;
			}
		} else if (ann.action().equals(AuditAction.DELETE)) {
			//take the first parameter
			if (parameters.length > 0 && parameters[0] instanceof AbstractDataEO) {
				return (AbstractDataEO) parameters[0];
			}
		}
		if (result != null) {
			return result;
		}
		return null;
	}
	
	private Integer getIdOfObject(Object entity) {
		if (entity instanceof AbstractDataEO) {
			return (Integer) ((AbstractDataEO) entity).getId();
		}
		return null;
	}
	
	private String getReadableMessage(AuditObject obj, AuditAction action, Object res, Object[] args) {
		switch (obj) {
			case USAGE:
				return getReadableMessage(action, (UsageEO) res);
			case PAYMENT:
				return getReadableMessage(action, (PaymentEO) res);
			case USER:
				return getReadableMessage(action, (UserEO) res);
			case SECURITY:
				return getReadableMessageForSecurity(action, res, args);
//			case ACCESS_DOOR:
//				return getReadableMessage(action, (AccessDoorResponse) res);
			case SUBSCRIPTION:
				return getReadableMessage(action, (SubscriptionEO) res);
			default:
				return "ERROR object " + obj + " not implemented yet";
		}
	}

	//FIXME take care of action
	private String getReadableMessage(AuditAction action, UsageEO usage) {
		StringBuilder sb = new StringBuilder();
		sb.append(usage.getUser().getFirstLastName());
		sb.append(" used the machine ");
		sb.append(usage.getMachine().getName());
		sb.append(" for ");
		sb.append(usage.getMinutes()).append("min");
		sb.append(" with ");
		if (usage.getAdditionalCost() == 0) {
			sb.append("no additional cost");
		} else {
			sb.append(usage.getAdditionalCost()).append("CHF of additional cost");
		}
		return sb.toString();
	}

	//FIXME take care of action
	private String getReadableMessage(AuditAction action, PaymentEO payment) {
		StringBuilder sb = new StringBuilder();
		if (action == AuditAction.INSERT) {
			sb.append(payment.getUser().getFirstLastName());
			sb.append(" paid ");
			sb.append(payment.getTotal());
			sb.append("CHF ");
		} else if (action == AuditAction.DELETE) {
			sb.append("Payment of ");
			sb.append(payment.getTotal());
			sb.append(" by ");
			sb.append(payment.getUser().getFirstLastName());
			sb.append(" was removed");
		}
		return sb.toString();
	}
	
	private String getReadableMessage(AuditAction action, UserEO user) {
		StringBuilder sb = new StringBuilder();
		sb.append("User ");
		sb.append(user.getEmail());
		switch (action) {
			case INSERT:
				sb.append(" inserted : ");
				break;
			case UPDATE:
				sb.append(" edited : ");
				break;
			case DELETE:
				sb.append(" deleted : ");
				break;
		}
		sb.append("firstname=");
		sb.append(user.getFirstname());
		sb.append(" lastname=");
		sb.append(user.getLastname());
		sb.append(" rfid=");
		sb.append(user.getRfid());
		sb.append(" authMachine=[");
		sb.deleteCharAt(sb.length() - 1);//remove last coma
		sb.append("]");
		return sb.toString();
	}
	
	private String getReadableMessageForSecurity(AuditAction action, Object result, Object[] args) {
		StringBuilder sb = new StringBuilder();
		if (action == AuditAction.LOGIN) {
			sb.append("Login result=");
			LoginResult res = (LoginResult) result;
			sb.append(res);
		} else if (action == AuditAction.LOGOUT) {
			sb.append("Logout");
		} else {
			sb.append("Unknown security action : ").append(action);
		}
		return sb.toString();
	}

//	private String getReadableMessage(AuditAction action, AccessDoorResponse res) {
//		StringBuilder sb = new StringBuilder();
//		if (res.getUser() != null) {
//			sb.append("User ");
//			sb.append(res.getUser().getFirstLastName());
//		} else {
//			sb.append("Unknown ");
//			sb.append(res.getRfid());
//		}
//		if (res.isGranted()) {
//			sb.append(" oppened the door");
//		} else {
//			sb.append(" could not open the door");
//		}
//		return sb.toString();
//	}
	private String getReadableMessage(AuditAction action, SubscriptionEO subscription) {
		StringBuilder sb = new StringBuilder();
		sb.append("User ");
		sb.append(subscription.getUser().getEmail());
		sb.append(" confirmed his subscription");
		return sb.toString();
	}
	
}
