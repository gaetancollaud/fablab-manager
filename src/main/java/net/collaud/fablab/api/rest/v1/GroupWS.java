package net.collaud.fablab.api.rest.v1;

import net.collaud.fablab.api.dao.GroupDao;
import net.collaud.fablab.api.rest.v1.data.UserSimpleTO;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import net.collaud.fablab.api.security.RolesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/group")
@Secured(RolesHelper.ROLE_ADMIN)
public class GroupWS {


	@Autowired
	private GroupDao groupDao;
	
	@RequestMapping(method = RequestMethod.GET)
	@Secured(RolesHelper.ROLE_MANAGE_USER)
	public DataModel<Iterable<UserSimpleTO>> list() {
		return new DataModel(groupDao.findAllWithRoles());
	}

}
