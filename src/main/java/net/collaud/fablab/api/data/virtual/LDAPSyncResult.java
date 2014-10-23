package net.collaud.fablab.api.data.virtual;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaetan
 */
public class LDAPSyncResult {

	private List<String> usersAdded;
	private List<String> usersDisabled;

	public LDAPSyncResult() {
		usersAdded = new ArrayList<>();
		usersDisabled = new ArrayList<>();
	}

	public void userAdded(String str) {
		usersAdded.add(str);
	}

	public void userDisabled(String str) {
		usersDisabled.add(str);
	}

	public List<String> getUsersAdded() {
		return usersAdded;
	}

	public List<String> getUsersDisabled() {
		return usersDisabled;
	}

}
