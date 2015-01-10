package net.collaud.fablab.api.security;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class RolesHelper {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MANAGE_MACHINE = "ROLE_MANAGE_MACHINE";
	public static final String ROLE_MANAGE_PAYMENT = "ROLE_MANAGE_PAYMENT";
	public static final String ROLE_MANAGE_USER = "ROLE_MANAGE_USER";
	public static final String ROLE_SYSTEM = "ROLE_SYSTEM";
	public static final String ROLE_VIEW_ACCOUNTING = "ROLE_VIEW_ACCOUNTING";
	public static final String ROLE_VIEW_AUDIT = "ROLE_VIEW_AUDIT";
	public static final String ROLE_MANAGE_RESERVATION = "ROLE_MANAGE_RESERVATION";
	public static final String ROLE_USE_RESERVATION = "ROLE_USE_RESERVATION";
	public static final String ROLE_VIEW_RESERVATION = "ROLE_VIEW_RESERVATION";

	public static final String[] LIST_ROLES = new String[]{
		ROLE_ADMIN,
		ROLE_MANAGE_MACHINE,
		ROLE_MANAGE_PAYMENT,
		ROLE_MANAGE_USER,
		ROLE_SYSTEM,
		ROLE_VIEW_ACCOUNTING,
		ROLE_VIEW_AUDIT,
		ROLE_MANAGE_RESERVATION,
		ROLE_USE_RESERVATION,
		ROLE_VIEW_RESERVATION
	};

}
