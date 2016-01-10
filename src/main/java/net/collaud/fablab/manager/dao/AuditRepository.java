package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.AuditEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Transactional
public interface AuditRepository extends JpaRepository<AuditEO, Integer>, AuditRepositoryCustom{


}
