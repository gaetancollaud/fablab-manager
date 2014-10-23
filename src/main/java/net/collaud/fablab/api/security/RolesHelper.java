package net.collaud.fablab.api.security;

/**
 *
 * @author gaetan
 */
public class RolesHelper {

	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_MANAGE_ACCESS = "manage_access";
	public static final String ROLE_MANAGE_MACHINES = "manage_machines";
	public static final String ROLE_MANAGE_PAYMENT = "manage_payment";
	public static final String ROLE_MANAGE_USERS = "manage_users";
	public static final String ROLE_SYSTEM = "system";
	public static final String ROLE_VIEW_ACCOUNTING = "view_accounting";
	public static final String ROLE_VIEW_AUDIT = "view_audit";
	public static final String ROLE_MANAGE_WORKSHOPS = "manage_workshop";
	public static final String ROLE_VIEW_MACHINES = "view_machines";
	
	public static final String HAS_ROLE_ADMIN = "hasRole('"+ROLE_ADMIN+"')";
	
	public static String hasRoleAdmin(){
		return hasAnyRole(ROLE_ADMIN);
	}

	public static String hasAnyRole(String... roles) {
		StringBuilder sb = new StringBuilder("hasAnyRole([");
		if (roles.length > 0) {
			for (String role : roles) {
				sb.append("'");
				sb.append(role);
				sb.append("',");
			}
			//remove last coma
			sb.deleteCharAt(sb.length());
		}
		sb.append("]");
		return sb.toString();
	}

}
