package net.collaud.fablab.api.rest.v1.data;

/**
 *
 * @author Gaétan
 */
public class UserTO extends AbstractTO{
	
	
	private Integer userId;
	private String login;
	private String firstname;
	private String lastname;

	public UserTO() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
