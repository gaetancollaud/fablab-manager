package net.collaud.fablab.manager.data.type;

import java.util.Optional;
import lombok.Getter;

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
	CURRENCY("$"),
	UPLOAD_MIME_ALLOWED("image/gif,image/jpg");
	
	@Getter
	private final String def;

	private ConfigurationKey(String def) {
		this.def = def;
	}
}
