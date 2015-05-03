package net.collaud.fablab.api.rest.v1.result;

import java.util.Set;
import lombok.Getter;
import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> 
 */
@Getter
public class ConnectedUser {
	boolean connected;
	private Set<String> roles;
	private UserEO user;
	
	public ConnectedUser(){
		this.connected = false;
	}

	public ConnectedUser(UserEO user, Set<String> roles) {
		this.connected = true;
		this.user = user;
		this.roles = roles;
	}
}
