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
	SYSTEM_STATUS("SystemStatus"),
	MACHINE("Machine"),
	PROJECT("Project"),
	ASSET("ASSET");
	private final String name;

	AuditObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
