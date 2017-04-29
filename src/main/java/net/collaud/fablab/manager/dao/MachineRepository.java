package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.MachineEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineRepository extends JpaRepository<MachineEO, Long>, MachineRepositoryCustom{
	
}
