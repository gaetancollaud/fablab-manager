package net.collaud.fablab.manager.data.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConfigurationKey {
	ACCOUNTING_EDIT_HISTORY_LIMIT("accounting.edition_limit", "7"),
	FABLAB_NAME("global.name", "No-Name fablab"),
	FABLAB_URL("global.url", "http://perdu.com"),
	GOOGLE_CALENDAR_API_KEY("google.calendar.api", ""),
	RECAPTCHA_SITE_KEY("google.recaptcha.site", ""),
	RECAPTCHA_SECRET("google.recaptcha.secret", ""),
	CURRENCY("global.currency", "$"),
	UPLOAD_MIME_ALLOWED("global.mime.allowed", "image/gif,image/jpg");

	@Getter
	private final String key;

	@Getter
	private final String def;

}
