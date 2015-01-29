package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.RoleEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface RoleRepository extends JpaRepository<RoleEO, Integer>{
}
