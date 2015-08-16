package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.RoleEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface RoleRepository extends JpaRepository<RoleEO, Integer>{
}
