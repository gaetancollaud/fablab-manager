package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.MachineEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineRepositoryCustom{

	List<MachineEO> findAll();
	
	MachineEO findOneWithDescription(Long machineId);

}
