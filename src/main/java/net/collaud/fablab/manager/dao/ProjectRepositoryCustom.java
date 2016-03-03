package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.ProjectEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ProjectRepositoryCustom{

	List<ProjectEO> findAll();

}
