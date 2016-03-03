package net.collaud.fablab.manager.dao.projection;

import com.mysema.query.types.Expression;
import net.collaud.fablab.manager.data.ProjectEO;
import net.collaud.fablab.manager.data.ProjectUserEO;
import net.collaud.fablab.manager.data.QProjectEO;
import net.collaud.fablab.manager.data.QProjectUserEO;

/**
 *
 * @author Gaetan Collaud
 */
public class ProjectProjection {

	public static final Expression<ProjectEO> projectionWithoutContent(QProjectEO project) {
		return QProjectEO.create(
				project.id,
				project.title,
				project.introduction,
				project.state,
				project.image_url,
				project.dateStart,
				project.dateEnd);
	}
	
	public static final Expression<ProjectUserEO> projectionProjectUser(QProjectUserEO projectUser){
		return QProjectUserEO.create(
				projectUser.userId,
				projectUser.canEdit,
				projectUser.role,
				UserProjections.getSimpleUser(projectUser.user)
		);
	}

}
