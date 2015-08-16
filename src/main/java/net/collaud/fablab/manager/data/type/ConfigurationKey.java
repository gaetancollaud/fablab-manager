package net.collaud.fablab.manager.data.type;

import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public enum ConfigurationKey {
	ACCOUNTING_EDIT_HISTORY_LIMIT("7"),
	FABLAB_NAME("No-Name fablab"),
	FABLAB_URL("http://perdu.com"),
	GOOGLE_CALENDAR_API_KEY(""),
	RECAPTCHA_SITE_KEY(""),
	RECAPTCHA_SECRET(""),
	CURRENCY("$");
	
	@Getter
	private final Optional<String> def;

	private ConfigurationKey(String def) {
		this.def = Optional.ofNullable(def);
	}
}
