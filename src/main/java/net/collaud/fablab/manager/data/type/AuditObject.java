package net.collaud.fablab.manager.data.type;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public enum AuditObject {

	ACCESS_DOOR("Door"),
	ACCESS_MACHINE("Machine"),
	SUBSCRIPTION("Subscription"),
	PAYMENT("Payment"),
	USAGE("Usage"),
	RESERVATION("Reservation"),
	USER("User"),
	SECURITY("Security"),
	SYSTEM_STATUS("SystemStatus");
	private final String name;

	private AuditObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
