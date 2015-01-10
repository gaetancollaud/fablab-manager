package net.collaud.fablab.api.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.collaud.fablab.api.data.type.AuditAction;
import net.collaud.fablab.api.data.type.AuditObject;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Inherited
//@InterceptorBinding//FIXME
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AuditDetail {
	public AuditObject object();
	public AuditAction action();
}
