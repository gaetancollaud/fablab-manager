package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.exceptions.FablabException;

/**
 *
 * @author gaetan
 */
public interface UserService {

	UserEO findByLogin(String login) throws FablabException;

	Iterable<UserEO> getAllUsers() throws FablabException;

}
