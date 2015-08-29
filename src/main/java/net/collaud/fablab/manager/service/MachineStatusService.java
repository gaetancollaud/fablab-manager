package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.MachineStatusEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>MachineStatus</tt>.
* @author Fabien Vuilleumier
*/
public interface MachineStatusService extends ReadWriteService<MachineStatusEO> {

    MachineStatusEO getByLabel(String label);

}
