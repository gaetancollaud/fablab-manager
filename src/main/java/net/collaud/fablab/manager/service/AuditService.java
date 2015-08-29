package net.collaud.fablab.manager.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.AuditEO;
import net.collaud.fablab.manager.data.type.AuditObject;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface AuditService {

	AuditEO addEntry(AuditEO entry);

	List<AuditEO> search(Integer userId, List<AuditObject> type, Date after, Date before, String content, int limit);
	
}
