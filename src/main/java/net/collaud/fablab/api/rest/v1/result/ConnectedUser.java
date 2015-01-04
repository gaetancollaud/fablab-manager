package net.collaud.fablab.api.rest.v1.result;

import java.util.List;

/**
 *
 * @author Gaétan
 */
public class ConnectedUser {
	boolean connected;
	private String login;
	private List<String> roles;
	
	public ConnectedUser(){
		this.connected = false;
	}

	public ConnectedUser(String login, List<String> roles) {
		this.connected = true;
		this.roles = roles;
		this.login = login;
	}

	public boolean isConnected() {
		return connected;
	}

	public String getLogin() {
		return login;
	}

	public List<String> getRoles() {
		return roles;
	}

}
