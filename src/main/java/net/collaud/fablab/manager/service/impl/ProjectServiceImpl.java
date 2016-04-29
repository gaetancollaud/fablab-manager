package net.collaud.fablab.manager.service.impl;

import java.util.List;
import java.util.Optional;

import net.collaud.fablab.manager.dao.ProjectRepository;
import net.collaud.fablab.manager.data.ProjectEO;
import net.collaud.fablab.manager.data.ProjectUserEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.ProjectService;
import net.collaud.fablab.manager.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
public class ProjectServiceImpl extends AbstractServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SecurityService securityService;

	@Override
	public List<ProjectEO> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public Optional<ProjectEO> getById(Long id) {
		return Optional.ofNullable(projectRepository.findOneWithDescription(id));
	}

	@Override
	@Secured({Roles.PROJECT_MANAGE, Roles.PROJECT_CREATE})
	public ProjectEO save(ProjectEO entity) {
		checkSecurity(entity.getId());
		return projectRepository.save(entity);
	}

	@Override
	@Secured({Roles.PROJECT_MANAGE, Roles.PROJECT_CREATE})
	public void remove(Long id) {
		checkSecurity(id);
		projectRepository.delete(id);
	}

	public void checkSecurity(long projectId) {
		if (projectId == 0) {
			//This is a new project
			return;
		}

		if (!securityService.hasRole(Roles.ASSET_MANAGE)) {
			//current user is a manager
			return;
		}
		ProjectEO project = projectRepository.getOne(projectId);
		if (project.getProjectUsers().stream()
				.filter(pu -> pu.getCanEdit())
				.map(ProjectUserEO::getUserId)
				.anyMatch(id -> id==securityService.getCurrentUserId())) {
			//current user can edit project
			return;
		}
		//none of the above, the user cannot edit the project
		throw new PermissionDeniedDataAccessException("Not the owner", null);
	}

}
