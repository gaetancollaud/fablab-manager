package net.collaud.fablab.api.rest.v1.criteria;

/**
 *
 * @author Gaétan
 */
public class AuthCredential {
	String login;
	String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "login=" + login + ", password=*****";
	}
}
