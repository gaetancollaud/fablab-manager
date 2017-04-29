package net.collaud.fablab.manager.data.type;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public enum LoginResult {
	OK("ok"),
	UNKNOWN_USERNAME("username"),
	WRONG_PASSWORD("password"),
	INTERNAL_ERROR("internal_error"),
	ALREADY_CONNECTED("already_connected");
	
	private final String name;

	private LoginResult(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
