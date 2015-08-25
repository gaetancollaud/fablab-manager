package net.collaud.fablab.manager.dao.impl;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import net.collaud.fablab.manager.dao.AuditRepositoryCustom;
import net.collaud.fablab.manager.data.AuditEO;
import net.collaud.fablab.manager.data.QAuditEO;
import net.collaud.fablab.manager.data.QUserEO;
import net.collaud.fablab.manager.data.type.AuditObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
public class AuditRepositoryImpl implements AuditRepositoryCustom {

	private QAuditEO audit = QAuditEO.auditEO;
	private QUserEO user = QUserEO.userEO;

	private QueryDslJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	@Override
	public List<AuditEO> search(Integer userId, List<AuditObject> type, Date after, Date before, String content, int limit) {
		
		SQLQuery query = template.newSqlQuery().from(audit);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(userId!=null){
			predicates.add(user.id.eq(userId));
		}
		
		if(after!=null){
			predicates.add(audit.when.after(after));
		}
		if(before!=null){
			predicates.add(audit.when.before(before));
		}
		
		
		if(!predicates.isEmpty()){
			query = query.where(predicates.toArray(new Predicate[predicates.size()]));
		}
		
		//TODO fix projection
		return template.query(query, audit);
//		return query
////				.innerJoin(audit.who, user)
//				.list(audit);
		
		
		
		
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
//		return null;
	}

}
