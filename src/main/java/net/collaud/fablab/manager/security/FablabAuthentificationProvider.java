package net.collaud.fablab.manager.security;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.ConfigurationKey;
import net.collaud.fablab.manager.service.ConfigurationService;
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
@Slf4j
@Component
public class FablabAuthentificationProvider implements AuthenticationProvider {

	public static final String SYSTEM_USERNAME = "system";

	@Autowired
	private UserService userService;

	@Autowired
	private ConfigurationService configurationService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String password = authentication.getCredentials() != null
				? authentication.getCredentials().toString() : "";

		if (SYSTEM_USERNAME.equalsIgnoreCase(login)) {
			return authenticateSystem(password);
		}

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
				LOG.warn("Authentification failed for login={}", login);
				throw new BadCredentialsException("wrong password");
			}
		} else {
			LOG.warn("User not found. login={}", login);
			throw new UsernameNotFoundException("Username " + login + " not found");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	protected Authentication authenticateSystem(String password) {
		String secret = this.configurationService.getValue(ConfigurationKey.SYSTEM_SECRET);
		if (secret.equals(password)) {
			LOG.info("User system successfully authenticated");
			return new UsernamePasswordAuthenticationToken("-1", password, Collections.singleton(new SimpleGrantedAuthority(Roles.SYSTEM)));
		} else {
			LOG.info("User system failed to authenticate, wrong secret");
			throw new BadCredentialsException("wrong password");
		}
	}

}
