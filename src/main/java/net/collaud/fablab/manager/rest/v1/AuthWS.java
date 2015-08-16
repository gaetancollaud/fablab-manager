package net.collaud.fablab.manager.rest.v1;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.LoginResult;
import net.collaud.fablab.manager.rest.v1.criteria.AuthCredential;
import net.collaud.fablab.manager.rest.v1.result.ConnectedUser;
import net.collaud.fablab.manager.service.SecurityService;
import net.collaud.fablab.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@JavascriptAPIConstant("AUTH_API")
@RequestMapping("/v1/auth")
@Slf4j
public class AuthWS {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserService userService;

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
		return securityService.getConnectedUser();
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public void signup(@RequestBody UserEO user,
			@Param("recaptcha") String recaptcha){
		userService.signup(user, recaptcha);
	}
	
	@RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
	public void forgotPassword(@Param("email") String email,
			@Param("recaptcha") String recaptcha){
		userService.forgotPassword(email, recaptcha);
	}
	
}
