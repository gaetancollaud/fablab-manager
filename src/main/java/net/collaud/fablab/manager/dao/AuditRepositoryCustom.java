package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.AuditEO;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.exceptions.FablabException;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface AuditRepositoryCustom{

	public List<AuditEO> search(Integer userId, List<AuditObject> type, Date after, Date before, String content, int limit) throws FablabException;

}
