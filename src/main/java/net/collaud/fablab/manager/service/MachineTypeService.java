package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.MachineTypeEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>MachineType</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface MachineTypeService extends ReadWriteService<MachineTypeEO> {

    public List<PriceMachineEO> getPrices(Integer id);

    MachineTypeEO getId(String technicalname);

}
