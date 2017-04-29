package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.MachineTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineTypeRepository extends JpaRepository<MachineTypeEO, Long>{
	
}
