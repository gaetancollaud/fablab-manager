package net.collaud.fablab.manager.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Aspect
@Slf4j
@Component
public class AuditAspect {

//	@Around("@within(net.collaud.fablab.manager.audit.Audit) || @annotation(net.collaud.fablab.manager.audit.Audit)")
//	@Around("@annotation(net.collaud.fablab.manager.audit.Audit)")
	@Around("execution(* net.collaud.fablab.manager.service.*.*(..))")
	public Object serviceAction(ProceedingJoinPoint pjp) throws Throwable {
		if(((MethodSignature)pjp.getSignature()).getMethod().isAnnotationPresent(Audit.class)){
			// perform the monitoring actions
			Audit audit = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(Audit.class);

			log.warn("Intercepted method to audit");
		}
		return pjp.proceed();
	}

}
