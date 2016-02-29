package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.ProjectEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ProjectRepository extends JpaRepository<ProjectEO, Integer>{
	
	@Query("SELECT p "
			+ " FROM ProjectEO p"
			+ " LEFT JOIN FETCH p.projectUsers pu ")
	@Override
	List<ProjectEO> findAll();
}
