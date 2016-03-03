package net.collaud.fablab.manager.dao.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.ProjectRepositoryCustom;
import net.collaud.fablab.manager.dao.projection.ProjectProjection;
import net.collaud.fablab.manager.data.ProjectEO;
import net.collaud.fablab.manager.data.ProjectUserEO;
import net.collaud.fablab.manager.data.QProjectEO;
import net.collaud.fablab.manager.data.QProjectUserEO;
import net.collaud.fablab.manager.data.QUserEO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
@Transactional
@Slf4j
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

	private final QProjectEO project = QProjectEO.projectEO;
	private final QProjectUserEO projectUser = QProjectUserEO.projectUserEO;
	private final QUserEO user = QUserEO.userEO;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ProjectEO> findAll() {
		final List<ProjectEO> projects = new JPAQuery(entityManager)
				.from(project)
				.orderBy(project.id.desc())
				.list(ProjectProjection.projectionWithoutContent(project));

		//get the simple user (and not the full ones)
		final Set<Long> projectIds = projects.stream()
				.map(ProjectEO::getId)
				.collect(Collectors.toSet());

		final Map<Long, List<ProjectUserEO>> projectUserByProjectId = new JPAQuery(entityManager)
				.from(projectUser)
				.innerJoin(projectUser.user)
				.where(projectUser.projectId.in(projectIds))
				.list(ProjectProjection.projectionProjectUser(projectUser))
				.stream()
				.collect(Collectors.groupingBy(ProjectUserEO::getProjectId));

		projects.stream()
				.filter(p -> projectUserByProjectId.containsKey(p.getId()))
				.forEach(p -> p.setProjectUsers(new HashSet<>(projectUserByProjectId.get(p.getId()))));

		return projects;
	}

	@Override
	public ProjectEO findOneWithDescription(Long projectId) {
		final ProjectEO projectEO = new JPAQuery(entityManager)
				.from(project)
				.where(project.id.eq(projectId))
				.singleResult(ProjectProjection.projectionWithContent(project));

		final List<ProjectUserEO> projectUserEO = new JPAQuery(entityManager)
				.from(projectUser)
				.innerJoin(projectUser.user)
				.where(projectUser.projectId.eq(projectId))
				.list(ProjectProjection.projectionProjectUser(projectUser));

		projectEO.setProjectUsers(new HashSet<>(projectUserEO));

		return projectEO;
	}

}
