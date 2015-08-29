package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.SupplyEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *This is the Service interface for a <tt>Supply</tt>.
* @author Fabien Vuilleumier
*/
public interface SupplyService extends ReadWriteService<SupplyEO>{

    List<SupplyEO> stock();

    void addQuantity(Integer id, Float quantity);

}
