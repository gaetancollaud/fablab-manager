package net.collaud.fablab.api.rest.v1;

import java.util.List;
import net.collaud.fablab.api.dao.RoleDao;
import net.collaud.fablab.api.rest.v1.data.RoleTO;
import net.collaud.fablab.api.rest.v1.data.UserTO;
import net.collaud.fablab.api.rest.v1.helper.RoleTOHelper;
import net.collaud.fablab.api.rest.v1.helper.UserTOHelper;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaétan
 */
@RestController()
@RequestMapping("/v1/user")
public class UserWS {

	private static final Logger LOG = LogManager.getLogger(UserWS.class);

	@Autowired
	private UserTOHelper userHelper;
	
	@Autowired
	private RoleTOHelper roleHelper;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleDao roleDao;

	@RequestMapping()
	@Secured(RolesHelper.ROLE_MANAGE_USER)
	public List<UserTO> list() {
		try {
			return userHelper.fromEOList(userService.getAllUsers());
		} catch (Exception ex) {
			LOG.error("Cannot list uers", ex);
		}
		return null;
	}

	@RequestMapping("roles")
	public List<RoleTO> listRoles() {
		return roleHelper.fromEOList(roleDao.getAllRoles());
	}
	
	

}
