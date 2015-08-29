package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.AuditEO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface AuditRepository extends JpaRepository<AuditEO, Integer>, AuditRepositoryCustom{


}
