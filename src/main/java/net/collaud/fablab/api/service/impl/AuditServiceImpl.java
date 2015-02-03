package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.dao.AuditRepository;
import net.collaud.fablab.api.data.AuditEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.AuditObject;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.AuditService;
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
		return auditDAO.addEntry(entry);
	}

	@Override
	@Secured({Roles.AUDIT_VIEW})
	public List<AuditEO> search(UserEO user, List<AuditObject> type, Date after, Date before, String content, int limit) throws FablabException {
		return auditDAO.search(user, type, after, before, content, limit);
	}
}
