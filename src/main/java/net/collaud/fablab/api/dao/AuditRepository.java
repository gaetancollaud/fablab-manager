package net.collaud.fablab.api.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.AuditEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.AuditObject;
import net.collaud.fablab.api.exceptions.FablabException;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface AuditRepository {

	public AuditEO addEntry(AuditEO entry) throws FablabException;

	public List<AuditEO> search(UserEO user, List<AuditObject> type, Date after, Date before, String content, int limit) throws FablabException;

}
