package net.collaud.fablab.api.rest.v1;

import java.util.List;
import net.collaud.fablab.api.rest.v1.data.UserTO;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.security.annotation.HasRoleUser;
import net.collaud.fablab.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaétan
 */
@RestController()
@RequestMapping("/api/v1/user")
@PreAuthorize(RolesHelper.HAS_ROLE_ADMIN)
public class UserWS {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping()
	@HasRoleUser
	public List<UserTO> listModulesRegistered() {
		return UserTO.fromList(userService.getAllUsers());
	}
	
}
