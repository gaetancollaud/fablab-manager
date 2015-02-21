package net.collaud.fablab.api.rest.v1;

import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.dao.GroupRepository;
import net.collaud.fablab.api.rest.v1.model.BaseModel;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/group")
@JavascriptAPIConstant("GROUP_API")
public class GroupWS {

	@Autowired
	private GroupRepository groupDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public BaseModel list() {
		return new DataModel(groupDao.findAllWithRoles());
	}

}
