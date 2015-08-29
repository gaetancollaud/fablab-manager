package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.MachineStatusEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineService extends ReadWriteService<MachineEO> {

    List<MachineEO> getByStatusLabel(String Label);

    MachineEO findSimpleByCode(String code);
    
    MachineStatusEO saveStatus(Integer machineId, Integer machineStatusId);
}
