package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Transactional
public interface UserRepository extends JpaRepository<UserEO, Integer>{

	@Query("SELECT u "
			+ " FROM UserEO u "
			+ " LEFT JOIN FETCH u.groups g "
			+ " LEFT JOIN FETCH u.membershipType mt "
			+ " WHERE u.id=:id")
	UserEO findOneDetails(@Param("id")Integer id);
	
	@Query("SELECT u "
			+ " FROM UserEO u "
			+ " LEFT JOIN FETCH u.groups g "
			+ " LEFT JOIN FETCH g.roles "
			+ " WHERE u.id=:id")
	UserEO findOneByIdAndFetchRoles(@Param("id")Integer id);
	
	@Query("SELECT u "
			+ " FROM UserEO u "
			+ " WHERE u.login=:login OR u.email=:login")
	UserEO findByLogin(@Param("login") String login);
	
	@Override
	@Query("SELECT u FROM UserEO u WHERE u.enabled=1")
	List<UserEO> findAll();
	
}
