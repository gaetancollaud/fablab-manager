package net.collaud.fablab.api.rest.v1;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.criteria.AuthCredential;
import net.collaud.fablab.api.rest.v1.result.ConnectedUser;
import net.collaud.fablab.api.service.SecurityService;
import net.collaud.fablab.api.web.SpringPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/auth")
@Slf4j
public class AuthWS {
	
	@Autowired
	private SpringPropertiesUtil properties;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "isAuthenticated", method = RequestMethod.GET)
	public Boolean isAuthenticated() {
		return securityService.isAuthenticated();
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public LoginResult login(@RequestBody AuthCredential credential) {
		log.debug("Login for " + credential);
		return securityService.login(credential.getLogin(), credential.getPassword());
	}

	@RequestMapping(value = "logout")
	public void logout() {
		securityService.logout();
	}

	@RequestMapping(value = "current")
	public ConnectedUser getCurrentUser() {
		if (securityService.isAuthenticated()) {
			final UserEO eo = securityService.getCurrentUser();
			List<String> roles = new ArrayList<>();
			eo.getGroups().forEach(g -> g.getRoles().forEach(r -> roles.add(r.getTechnicalname())));
			ConnectedUser user = new ConnectedUser(eo.getFirstname(), eo.getLastname(), roles);
			return user;
		} else {
			return new ConnectedUser();
		}
	}
	
	
	@RequestMapping(value = "constants")
	public Map<String, String> constants() {
		return properties.getProperties(
				new AbstractMap.SimpleEntry<>("google.api", "GOOGLE_API")
		);
	}
}
