package net.collaud.fablab.api.service.impl;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author gaetan
 */
public class AbstractServiceImpl {

	private static final Logger LOG = Logger.getLogger(AbstractServiceImpl.class);

	protected String getCurrentUserLogin() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			return auth.getName();
		} catch (NullPointerException ex) {
			return null;
		}
	}

}
