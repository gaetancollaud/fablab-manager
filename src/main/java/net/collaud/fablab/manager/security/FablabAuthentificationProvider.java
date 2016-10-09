package net.collaud.fablab.manager.security;

import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Component
public class FablabAuthentificationProvider implements AuthenticationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(FablabAuthentificationProvider.class);

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String password = authentication.getCredentials() != null
				? authentication.getCredentials().toString() : "";

		final Optional<UserEO> opt = userService.findByLogin(login);
		if (opt.isPresent()) {
			UserEO user = opt.get();
			if (PasswordUtils.isPasswordValid(user, password, UserEO::getPasswordRequest)) {
				//the new password is correct enable it
				user = userService.acceptPasswordChange(user);
			}
			if (PasswordUtils.isPasswordValid(user, password, UserEO::getPassword)) {
				Set<GrantedAuthority> roles = new HashSet<>();
				List<String> groupsStr = new ArrayList<>();
				List<String> rolesStr = new ArrayList<>();
				user.getGroups()
						.forEach(g -> {
							groupsStr.add(g.getTechnicalname());
							g.getRoles()
									.forEach(r -> {
										roles.add(new SimpleGrantedAuthority(r.getTechnicalname()));
										rolesStr.add(r.getTechnicalname());
									});
						});

				LOG.info("Authentification success for user={}, groups={}, roles={}", login, groupsStr, rolesStr);

				return new UsernamePasswordAuthenticationToken(user.getId(), password, roles);
			} else {
				throw new BadCredentialsException("wrong password");
			}
		} else {
			throw new UsernameNotFoundException("Username " + login + " not found");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
