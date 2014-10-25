package net.collaud.fablab.api.security;

/**
 *
 * @author gaetan
 */
public class RolesHelper {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MANAGE_ACCESS = "ROLE_MANAGE_ACCESS";
	public static final String ROLE_MANAGE_MACHINE = "ROLE_MANAGE_MACHINE";
	public static final String ROLE_MANAGE_PAYMENT = "ROLE_MANAGE_PAYMENT";
	public static final String ROLE_MANAGE_USER = "ROLE_MANAGE_USER";
	public static final String ROLE_SYSTEM = "ROLE_SYSTEM";
	public static final String ROLE_VIEW_ACCOUNTING = "ROLE_VIEW_ACCOUNTING";
	public static final String ROLE_VIEW_AUDIT = "ROLE_VIEW_AUDIT";
	public static final String ROLE_MANAGE_WORKSHOP = "ROLE_MANAGE_WORKSHOP";
	public static final String ROLE_VIEW_MACHINE = "ROLE_VIEW_MACHINE";
	public static final String ROLE_MANAGE_RESERVATION = "ROLE_MANAGE_RESERVATION";
	public static final String ROLE_USE_RESERVATION = "ROLE_USE_RESERVATION";
	public static final String ROLE_VIEW_RESERVATION = "ROLE_VIEW_RESERVATION";

	public static final String hasAnyRole(String... roles) {
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
