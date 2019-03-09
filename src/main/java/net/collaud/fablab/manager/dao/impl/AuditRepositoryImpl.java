package net.collaud.fablab.manager.dao.impl;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAQueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.AuditRepositoryCustom;
import net.collaud.fablab.manager.data.AuditEO;
import net.collaud.fablab.manager.data.QAuditEO;
import net.collaud.fablab.manager.data.QUserEO;
import net.collaud.fablab.manager.data.type.AuditObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
@Transactional
@Slf4j
public class AuditRepositoryImpl implements AuditRepositoryCustom {

	public static final int MAX_LIMIT = 1000;
	public static final int DEFAULT_LIMIT = 100;

	private QAuditEO audit = QAuditEO.auditEO;
	private QUserEO user = QUserEO.userEO;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AuditEO> search(Long userId, List<AuditObject> type, Date after, Date before, String content, int limit) {

		if(limit<=0){
			limit = DEFAULT_LIMIT;
		}else if(limit > MAX_LIMIT ) {
			LOG.warn("Requested limit of {} which is more than the max allowed : {}", limit, MAX_LIMIT);
			limit = MAX_LIMIT;
		}

		JPAQuery<AuditEO> query = new JPAQuery<>(entityManager)
				.select(QAuditEO.create(
						audit.id,
						audit.action,
						audit.object,
						audit.objectId,
						audit.when,
						audit.success,
						audit.who,
						audit.content,
						audit.detail
				))
				.from(audit);

		List<Predicate> predicates = new ArrayList<>();

		if (userId != null) {
			predicates.add(user.id.eq(userId));
		}

		if (after != null) {
			predicates.add(audit.when.after(after));
		}
		if (before != null) {
			predicates.add(audit.when.before(before));
		}
		if (userId != null) {
			predicates.add(user.id.eq(userId));
		}
		if (type != null && type.size() > 0) {
			predicates.add(audit.object.in(type));
		}
		if (content != null) {
			predicates.add(audit.content.containsIgnoreCase(content)
					.or(audit.detail.containsIgnoreCase(content)));
		}

		if (!predicates.isEmpty()) {
			query = query.where(predicates.toArray(new Predicate[predicates.size()]));
		}

		return query
				.innerJoin(audit.who, user)
				.where(predicates.toArray(new Predicate[predicates.size()]))
				.orderBy(audit.when.desc())
				.limit(limit)
				.fetch();
	}

}
