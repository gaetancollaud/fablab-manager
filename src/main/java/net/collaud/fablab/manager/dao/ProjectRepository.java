package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.ProjectEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ProjectRepository extends JpaRepository<ProjectEO, Long>, ProjectRepositoryCustom{
	
}
