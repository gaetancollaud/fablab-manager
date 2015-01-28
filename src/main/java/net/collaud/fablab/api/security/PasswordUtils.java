package net.collaud.fablab.api.security;

import net.collaud.fablab.api.data.UserEO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
abstract public class PasswordUtils {

	public static final String DIGEST_ALGORITHM = "SHA-256";
	public static final int SALT_LEN = 40;

	private static MessageDigestPasswordEncoder getDigest() {
		return new MessageDigestPasswordEncoder(DIGEST_ALGORITHM);
	}

	public static boolean isPasswordValid(UserEO user, String password) {
//		return true;
		return getDigest().isPasswordValid(user.getPassword(), password, user.getPasswordSalt());
	}

	public static UserEO setUseEONewPassword(UserEO user, String newPassword) {
		final String salt = RandomStringUtils.random(SALT_LEN, true, true);
		user.setPassword(getDigest().encodePassword(newPassword, salt));
		user.setPasswordSalt(salt);
		return user;
	}
}
