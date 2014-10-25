package net.collaud.fablab.api.rest.v1;

import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import net.collaud.fablab.api.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaétan
 */
@RestController()
@RequestMapping("/api/v1/auth")
public class AuthWS {

	private static final Logger LOG = Logger.getLogger(AuthWS.class);

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value="isAuthenticated", method = RequestMethod.GET)
	public Boolean isAuthenticated() {
		return securityService.isAuthenticated();
	}

	@RequestMapping(value="login", method = RequestMethod.POST)
	public LoginResult login(@RequestBody AuthCredential credential) {
		LOG.debug("Login for " + credential);
		return securityService.login(credential.getLogin(), credential.getPassword());
	}

	@RequestMapping(value="logout")
	public void logout() {
		securityService.logout();
	}

}
