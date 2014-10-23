package net.collaud.fablab.api.rest.v1.data;

import java.util.ArrayList;
import java.util.List;
import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaétan
 */
public class UserTO {
	public static List<UserTO> fromList(List<UserEO> eo){
		List<UserTO> to = new ArrayList<>(eo.size());
		eo.forEach(u -> to.add(new UserTO(u)));
		return to;
	}
	
	private String login;
	private String firstname;
	private String lastname;

	public UserTO() {
	}

	public UserTO(UserEO eo) {
		this.login = eo.getLogin();
		this.firstname = eo.getFirstname();
		this.lastname = eo.getLastname();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
