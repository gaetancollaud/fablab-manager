package net.collaud.fablab.manager.security;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface Roles {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER_VIEW = "ROLE_USER_VIEW";
    public static final String USER_MANAGE = "ROLE_USER_MANAGE";
    public static final String MACHINE_VIEW = "ROLE_MACHINE_VIEW";
    public static final String MACHINE_MANAGE = "ROLE_MACHINE_MANAGE";
    public static final String PAYMENT_VIEW = "ROLE_PAYMENT_VIEW";
    public static final String PAYMENT_MANAGE = "ROLE_PAYMENT_MANAGE";
    public static final String ACCOUNTING_VIEW = "ROLE_ACCOUNTING_VIEW";
    public static final String ACCOUNTING_MANAGE = "ROLE_ACCOUNTING_MANAGE";
    public static final String AUDIT_VIEW = "ROLE_AUDIT_VIEW";
    public static final String RESERVATION_VIEW = "ROLE_RESERVATION_VIEW";
    public static final String RESERVATION_USE = "ROLE_RESERVATION_USE";
    public static final String RESERVATION_MANAGE = "ROLE_RESERVATION_MANAGE";
    public static final String MAILING_LIST_MANAGE = "ROLE_MAILINGLIST_MANAGE";
    public static final String TICKET_VIEW = "ROLE_TICKET_VIEW";
    public static final String TICKET_MANAGE = "ROLE_TICKET_MANAGE";
    public static final String SUPPLY_MANAGE = "ROLE_SUPPLY_MANAGE";
    public static final String SUPPLY_VIEW = "ROLE_SUPPLY_VIEW";
    public static final String TRAINING_VIEW = "ROLE_TRAINING_VIEW";
    public static final String TRAINING_MANAGE = "ROLE_TRAINING_MANAGE";
    public static final String GROUP_MANAGE = "ROLE_GROUP_MANAGE";
    public static final String EVENT_VIEW = "ROLE_EVENT_VIEW";
    public static final String EVENT_MANAGE = "ROLE_EVENT_MANAGE";
	public static final String SYSTEM = "ROLE_SYSTEM";

    public static final String[] LIST_ROLES = new String[]{
        ADMIN,
        USER_VIEW,
        USER_MANAGE,
        MACHINE_VIEW,
        MACHINE_MANAGE,
        PAYMENT_VIEW,
        PAYMENT_MANAGE,
        ACCOUNTING_VIEW,
        ACCOUNTING_MANAGE,
        AUDIT_VIEW,
        RESERVATION_VIEW,
        RESERVATION_USE,
        RESERVATION_MANAGE,
        MAILING_LIST_MANAGE,
        TICKET_VIEW,
        TICKET_MANAGE,
        SUPPLY_VIEW,
        SUPPLY_MANAGE,
        TRAINING_MANAGE,
        TRAINING_VIEW,
        GROUP_MANAGE,
        EVENT_VIEW,
        EVENT_MANAGE
    };
}
