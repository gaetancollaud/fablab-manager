package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.RoleEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *This is the DAO interface for a <tt>Role</tt>.
 * @author Fabien Vuilleumier
 */
public interface RoleRepository extends JpaRepository<RoleEO, Integer>{

}
