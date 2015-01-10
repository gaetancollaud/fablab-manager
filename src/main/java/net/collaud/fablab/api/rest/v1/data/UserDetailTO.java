package net.collaud.fablab.api.rest.v1.data;

import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
public class UserDetailTO extends AbstractTO<UserEO, UserDetailTO> {

	private Integer id;
	private String login;
	private String firstname;
	private String lastname;
	private String email;
	private double balance;
	private MembershipTypeTO membershipType;

	@Override
	public UserEO convertToEO() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public UserDetailTO fromEO(UserEO eo) {
		setId(eo.getUserId());
		setLogin(eo.getLogin());
		setFirstname(eo.getFirstname());
		setLastname(eo.getLastname());
		setEmail(eo.getEmail());
		setBalance(eo.getBalance());
		setMembershipType(new MembershipTypeTO().fromEO(eo.getMembershipType()));
		return this;
	}

	public UserDetailTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public MembershipTypeTO getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipTypeTO membershipType) {
		this.membershipType = membershipType;
	}
}
