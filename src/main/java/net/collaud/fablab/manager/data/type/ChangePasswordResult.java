package net.collaud.fablab.manager.data.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ChangePasswordResult {
	OK("ok"),
	WRONG_PASSWORD("wront_password"),
	WRONG_REPEAT("wront_repeat"),
	NOT_AUTHENTICATED("not_authenticated");

	private final String name;

}
