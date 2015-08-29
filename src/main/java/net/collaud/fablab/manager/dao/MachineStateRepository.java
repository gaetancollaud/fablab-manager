package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.MachineStateEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *This is the DAO interface for a <tt>MachineState</tt>.
 * @author Fabien Vuilleumier
 */
public interface MachineStateRepository extends JpaRepository<MachineStateEO, Integer>{

}
