package net.collaud.fablab.api.rest.v1.result;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> 
 */
@Getter
public class ConnectedUser {
	boolean connected;
	private Integer id;
	private String firstname;
	private String lastname;
	private List<String> roles;
	
	public ConnectedUser(){
		this.connected = false;
	}

	public ConnectedUser(Integer id, String firstname, String lastname, List<String> roles) {
		this.connected = true;
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = roles;
	}
}
