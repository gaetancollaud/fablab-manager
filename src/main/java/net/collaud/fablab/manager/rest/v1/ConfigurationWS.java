package net.collaud.fablab.manager.rest.v1;

import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.rest.v1.model.BaseModel;
import net.collaud.fablab.manager.rest.v1.model.DataModel;
import net.collaud.fablab.manager.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/api/v1/configuration")
@JavascriptAPIConstant("CONFIGURATION_API")
public class ConfigurationWS {

	@Autowired
	private ConfigurationService configurationService;


	@RequestMapping(method = RequestMethod.GET)
	public BaseModel getallMembershipType() {
		return new DataModel(configurationService.getAllConfiguration());
	}

}
