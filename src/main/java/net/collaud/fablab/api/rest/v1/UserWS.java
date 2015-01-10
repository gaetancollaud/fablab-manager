package net.collaud.fablab.api.rest.v1;

import net.collaud.fablab.api.dao.RoleDao;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.rest.v1.data.AbstractTO;
import net.collaud.fablab.api.rest.v1.data.UserDetailTO;
import net.collaud.fablab.api.rest.v1.data.UserSimpleTO;
import net.collaud.fablab.api.rest.v1.model.BaseModel;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/user")
@Secured(RolesHelper.ROLE_ADMIN)
public class UserWS {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleDao roleDao;

	@RequestMapping(method = RequestMethod.GET)
	@Secured(RolesHelper.ROLE_MANAGE_USER)
	public DataModel<Iterable<UserSimpleTO>> list() {
		return new DataModel(AbstractTO.fromEOList(userService.getAllUsers(), UserEO.class, UserSimpleTO.class));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@Secured(RolesHelper.ROLE_MANAGE_USER)
	public BaseModel get(@PathVariable Integer id) {
		return new DataModel(userService.findById(id));
	}

	@RequestMapping(value = "{id}")
	@Secured(RolesHelper.ROLE_MANAGE_USER)
	public BaseModel save(UserEO user) {
		return new DataModel(userService.save(user));
	}

}
