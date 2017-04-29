package net.collaud.fablab.manager.dao.projection;

import com.mysema.query.types.Expression;
import net.collaud.fablab.manager.data.QUserEO;
import net.collaud.fablab.manager.data.UserEO;

/**
 *
 * @author Gaetan Collaud
 */
public class UserProjections {
	public static final Expression<UserEO> getSimpleUser(QUserEO user){
		return QUserEO.create(user.id, user.firstname, user.lastname, user.email);
	}
}
