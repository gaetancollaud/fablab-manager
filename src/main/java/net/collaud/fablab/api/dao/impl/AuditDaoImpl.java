package net.collaud.fablab.api.dao.impl;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.dao.AuditRepository;
import net.collaud.fablab.api.data.AuditEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.type.AuditObject;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */

@Repository
public class AuditDaoImpl extends AbstractDAO<AuditEO> implements AuditRepository {
	
	public AuditDaoImpl() {
		super(AuditEO.class);
	}
	
	@Override
	public AuditEO addEntry(AuditEO lua) {
		return null;
		//return create(lua);
	}
	
	@Override
	public List<AuditEO> search(UserEO user, List<AuditObject> type, Date after, Date before, String content, int limit) {
//		if (after != null && before != null && after.after(before)) {
//			return new ArrayList<>();
//		}
//		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<AuditEO> cq = cb.createQuery(AuditEO.class);
//		Root<AuditEO> audit = cq.from(AuditEO.class);
//		
//		List<Predicate> listPredicate = new ArrayList<>();
//		if (user != null) {
//			listPredicate.add(cb.equal(audit.get(AuditEO_.who), user));
//		}
//		
//		if (type != null && !type.isEmpty()) {
//			listPredicate.add(audit.get(AuditEO_.object).in(type));
//		}
//		
//		if (after != null) {
//			listPredicate.add(cb.greaterThanOrEqualTo(audit.get(AuditEO_.when), after));
//		}
//		if (before != null) {
//			listPredicate.add(cb.lessThanOrEqualTo(audit.get(AuditEO_.when), before));
//		}
//		if(content!=null && !content.trim().isEmpty()){
//			listPredicate.add(cb.like(audit.get(AuditEO_.content), "%"+content+"%"));
//		}
//		
//		cq.where(cb.and(listPredicate.toArray(new Predicate[]{})));
//		
//		cq.orderBy(cb.desc(audit.get(AuditEO_.when)));
//		TypedQuery<AuditEO> query = getEntityManager().createQuery(cq);
//		query.setMaxResults(limit);
//		return query.getResultList();
		return null;
	}
	
}
