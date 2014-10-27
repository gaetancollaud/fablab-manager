package net.collaud.fablab.api.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Gaétan
 */
public class FablabAuthentificationProvider implements AuthenticationProvider {

	private static final Logger LOG = Logger.getLogger(FablabAuthentificationProvider.class);

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserEO user;
		
		try {
			user = userService.findByLogin(login);
		} catch (Exception ex) {
			throw new AuthenticationServiceException("Cannot get user with login " + login, ex);
		}
		if (user != null) {
//
//			if(mdpwrong){
//				throw new BadCredentialsException("wrong password");
//			}

			//FIXME test mdp
			Set<GrantedAuthority> roles = new HashSet<>();
			List<String> groupsStr = new ArrayList<>();
			List<String> rolesStr = new ArrayList<>();
			user.getGroups()
					.forEach(g -> g.getRoles()
							.forEach(r -> {
								roles.add(new SimpleGrantedAuthority(r.getTechnicalname()));
								rolesStr.add(r.getTechnicalname());
							}));
			LOG.info("Authentification success for user=" + login + ", groups=" + groupsStr + ", roles=" + rolesStr);
			//FIXME audit this

			return new UsernamePasswordAuthenticationToken(login, password, roles);
		} else {
			throw new UsernameNotFoundException("Username " + login + " not found");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
