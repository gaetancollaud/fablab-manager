package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.MembershipTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MembershipTypeDao extends JpaRepository<MembershipTypeEO, Integer>{

}