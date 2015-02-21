package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.model.BaseModel;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import net.collaud.fablab.api.service.MembershipTypeService;
import net.collaud.fablab.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/user")
@JavascriptAPIConstant("USER_API")
public class UserWS extends ReadWriteRestWebservice<UserEO, UserService> {

	@Autowired
	private UserService userService;

	@Autowired
	private MembershipTypeService membershipTypeService;

	@PostConstruct
	private void postConstruct() {
		super.setService(userService);
	}

	//FIXME put in standalone WS
	@RequestMapping(value = "membershipType", method = RequestMethod.GET)
	public BaseModel getallMembershipType() {
		return new DataModel(membershipTypeService.findAll());
	}

}
