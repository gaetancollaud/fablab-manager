package net.collaud.fablab.api.service.impl;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gaetan
 */
@RolesAllowed({RolesHelper.ROLE_ADMIN})
@Service
@Transactional
public class SecurityServiceImpl extends AbstractServiceImpl implements SecurityService {

	private static final Logger LOG = Logger.getLogger(SecurityServiceImpl.class);

	@Autowired
	private UserDao userDao;

	

	@Override
	@PermitAll
	public UserEO getCurrentUser() throws FablabException {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return null;
		}
		return userDao.getByLogin(context.getAuthentication().getName());
	}

}
