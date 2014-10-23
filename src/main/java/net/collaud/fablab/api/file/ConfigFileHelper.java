package net.collaud.fablab.api.file;

import net.collaud.fablab.common.file.KeyEnum;

/**
 *
 * @author gaetan
 */
public enum ConfigFileHelper implements KeyEnum {

	DEV_MODE,
	ITEM_PER_PAGE,
	LDAP_URL,
	DEFAULT_MEMBERSHIP_TYPE,
	PASSWORD_SALT,
	WEBSERVICE_TOKEN,
	SHOW_RESERVATION;

	@Override
	public String getKey() {
		return toString();
	}

}
