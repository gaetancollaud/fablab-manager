package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.UserRepository;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.LoginResult;
import net.collaud.fablab.api.rest.v1.result.ConnectedUser;
import net.collaud.fablab.api.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
public class SecurityServiceImpl extends AbstractServiceImpl implements SecurityService {

	private static final Logger LOG = LoggerFactory
			.getLogger(SecurityServiceImpl.class);

	@Autowired
	private UserRepository userDao;

	@Autowired
	private AuthenticationProvider authManager;

	@Override
	public Optional<UserEO> getCurrentUser() {
		Integer id = getCurrentUserId();
		if (id < 0) {
			return Optional.empty();
		}
		return userDao.findOneByIdAndFetchRoles(id);
	}

	@Override
	public ConnectedUser getConnectedUser() {
		if (isAuthenticated()) {
			final Optional<UserEO> eo = getCurrentUser();
			if (eo.isPresent()) {
				List<String> roles = new ArrayList<>();
				eo.get().getGroups().forEach(g -> g.getRoles().forEach(r -> roles.add(r.getTechnicalname())));
				ConnectedUser user = new ConnectedUser(eo.get().getId(), eo.get().getFirstname(), eo.get().getLastname(), roles);
				return user;
			} else {
				return new ConnectedUser();
			}
		} else {
			return new ConnectedUser();
		}
	}

	@Override
	public Integer getCurrentUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return -1;
		}
		return Integer.parseInt(context.getAuthentication().getName());
	}

	@Override
	public Boolean isAuthenticated() {
		SecurityContext context = SecurityContextHolder.getContext();
		return context != null && context.getAuthentication() != null;
	}

	@Override
	public LoginResult login(String login, String password) {
		if (isAuthenticated()) {
			return LoginResult.ALREADY_CONNECTED;
		}
		try {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
			SecurityContextHolder.getContext().setAuthentication(auth);
			return LoginResult.OK;
		} catch (BadCredentialsException ex) {
			LOG.warn("Wrong password for login " + login);
			return LoginResult.WRONG_PASSWORD;
		} catch (UsernameNotFoundException ex) {
			LOG.warn("Login " + login + " not found");
			return LoginResult.UNKNOWN_USERNAME;
		} catch (AuthenticationException ex) {
			LOG.error("Error while login", ex);
			return LoginResult.INTERNAL_ERROR;
		}
	}

	@Override
	public void logout() {
		SecurityContextHolder.clearContext();
	}

}
