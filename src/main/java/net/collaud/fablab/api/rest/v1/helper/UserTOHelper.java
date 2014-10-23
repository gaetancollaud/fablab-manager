package net.collaud.fablab.api.rest.v1.helper;

import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.rest.v1.data.UserTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaétan
 */
@Component
public class UserTOHelper extends AbstractTOHelper<UserTO, UserEO>{

	@Override
	public UserEO fromTO(UserTO to) {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public UserTO fromEO(UserEO eo) {
		UserTO to = new UserTO();
		to.setUserId(eo.getUserId());
		to.setLogin(eo.getLogin());
		to.setFirstname(eo.getFirstname());
		to.setLastname(eo.getLastname());
		return to;
	}

	
}
