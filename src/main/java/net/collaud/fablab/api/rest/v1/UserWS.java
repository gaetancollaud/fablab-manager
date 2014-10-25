package net.collaud.fablab.api.rest.v1;

import java.util.List;
import net.collaud.fablab.api.rest.v1.data.UserTO;
import net.collaud.fablab.api.rest.v1.helper.UserTOHelper;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaétan
 */
@RestController()
@RequestMapping("/api/v1/user")
public class UserWS {

	private static final Logger LOG = Logger.getLogger(UserWS.class);

	@Autowired
	private UserTOHelper userHelper;

	@Autowired
	private UserService userService;

	@RequestMapping()
	@PreAuthorize("hasRole('manage_users')")
	//@Secured(RolesHelper.ROLE_MANAGE_USERS)
	public List<UserTO> list() {
		try {
			return userHelper.fromEOList(userService.getAllUsers());
		} catch (Exception ex) {
			LOG.error("Cannot list uers", ex);
		}
		return null;
	}

}
