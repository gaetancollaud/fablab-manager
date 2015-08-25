package net.collaud.fablab.manager.service.impl;

import com.mysema.query.types.Predicate;
import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.dao.AuditRepository;
import net.collaud.fablab.manager.data.AuditEO;
import net.collaud.fablab.manager.data.QAuditEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Secured({Roles.ADMIN})
public class AuditServiceImpl extends AbstractServiceImpl implements AuditService {

	@Autowired
	private AuditRepository auditDAO;

	@Override
	public AuditEO addEntry(AuditEO entry) throws FablabException {
		return auditDAO.save(entry);
	}

	@Override
	@Secured({Roles.AUDIT_VIEW})
	public List<AuditEO> search(Integer userId, List<AuditObject> type, Date after, Date before, String content, int limit) throws FablabException {
		return auditDAO.search(userId, type, after, before, content, limit);
	}
}
