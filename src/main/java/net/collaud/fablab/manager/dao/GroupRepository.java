package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.GroupEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface GroupRepository extends JpaRepository<GroupEO, Long>{
	
	@Query("SELECT DISTINCT g FROM GroupEO g LEFT JOIN FETCH g.roles")
	List<GroupEO> findAllWithRoles();

}
