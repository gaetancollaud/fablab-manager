package net.collaud.fablab.manager.data.virtual;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.collaud.fablab.manager.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@EqualsAndHashCode(of = {"id"})
public class HistoryEntryUser  {

	private final int id;
	private final String lastname;
	private final String firstname;
	public HistoryEntryUser(UserEO user){
		this.id = user.getId();
		this.lastname = user.getLastname();
		this.firstname = user.getFirstname();
	}

}
