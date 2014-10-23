package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.exceptions.FablabException;

/**
 *
 * @author gaetan
 */
public interface SecurityService {

	UserEO getCurrentUser() throws FablabException;
	
}
