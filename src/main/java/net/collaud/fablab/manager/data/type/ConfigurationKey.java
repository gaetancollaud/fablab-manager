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
	ACCOUNTING_EDIT_HISTORY_LIMIT("accounting.edition_limit", "7", true),
	FABLAB_NAME("global.name", "No-Name fablab", true),
	FABLAB_URL("global.url", "http://perdu.com", true),
	GOOGLE_CALENDAR_API_KEY("google.calendar.api", "", true),
	RECAPTCHA_SITE_KEY("google.recaptcha.site", "", true),
	RECAPTCHA_SECRET("google.recaptcha.secret", "", true),
	CURRENCY("global.currency", "$", true),
	UPLOAD_MIME_ALLOWED("global.mime.allowed", "image/gif,image/jpg", true),
	SYSTEM_SECRET("system.secret", "secret", false);

	@Getter
	private final String key;

	@Getter
	private final String def;

	@Getter
	private final boolean isPublic;

}
