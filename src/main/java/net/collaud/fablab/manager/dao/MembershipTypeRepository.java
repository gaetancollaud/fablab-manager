package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.MembershipTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MembershipTypeRepository extends JpaRepository<MembershipTypeEO, Long>{

}