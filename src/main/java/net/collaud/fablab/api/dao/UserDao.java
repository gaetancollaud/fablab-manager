package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.UserEO;
/**
 *
 * @author gaetan
 */
public interface UserDao {

	UserEO getById(Integer id);
	UserEO getByLogin(String login);
	List<UserEO> getAllUsers();
}
