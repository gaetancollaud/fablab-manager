package net.collaud.fablab.api.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.collaud.fablab.api.security.RolesHelper;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author Gaétan
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('"+RolesHelper.ROLE_MANAGE_USERS+"')")
public @interface HasRoleUser {
	
}
