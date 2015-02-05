package net.collaud.fablab.api.rest.v1.result;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> 
 */
@Getter
public class ConnectedUser {
	boolean connected;
	private String firstname;
	private String lastname;
	private List<String> roles;
	
	public ConnectedUser(){
		this.connected = false;
	}

	public ConnectedUser(String firstname, String lastname, List<String> roles) {
		this.connected = true;
		this.roles = roles;
		this.firstname = firstname;
		this.lastname = lastname;
	}
}
