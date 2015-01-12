package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Transactional
public interface UserDao extends JpaRepository<UserEO, Integer>{

	@Query("SELECT u "
			+ " FROM UserEO u "
			+ " LEFT JOIN FETCH u.groups g "
			+ " LEFT JOIN FETCH u.membershipType mt "
			+ " WHERE u.userId=:id")
	UserEO findOneDetails(@Param("id")Integer id);
	
	@Query("SELECT u "
			+ " FROM UserEO u "
			+ " LEFT JOIN FETCH u.groups g "
			+ " LEFT JOIN FETCH g.roles "
			+ " WHERE u.userId=:id")
	UserEO findOneByIdAndFetchRoles(@Param("id")Integer id);
	
	@Query("SELECT u "
			+ " FROM UserEO u "
			+ " WHERE u.login=:login OR u.email=:login")
	UserEO findByLogin(@Param("login") String login);
	
}
