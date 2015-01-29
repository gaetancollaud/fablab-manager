package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.GroupEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface GroupRepository extends JpaRepository<GroupEO, Integer>{
	
	@Query("SELECT DISTINCT g FROM GroupEO g LEFT JOIN FETCH g.roles")
	List<GroupEO> findAllWithRoles();

}
