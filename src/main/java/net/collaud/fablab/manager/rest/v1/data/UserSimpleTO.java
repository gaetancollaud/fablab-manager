package net.collaud.fablab.manager.rest.v1.data;

import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.MembershipTypeEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
public class UserSimpleTO extends AbstractTO<UserEO, UserSimpleTO> {

	private Integer id;
	private String login;
	private String firstname;
	private String lastname;
	private String email;
	private double balance;
	private MembershipTypeEO membershipType;

	@Override
	public UserEO convertToEO() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public UserSimpleTO fromEO(UserEO eo) {
		setId(eo.getId());
		setLogin(eo.getLogin());
		setFirstname(eo.getFirstname());
		setLastname(eo.getLastname());
		setEmail(eo.getEmail());
		setMembershipType(eo.getMembershipType());
		return this;
	}

	public UserSimpleTO() {
	}

}
