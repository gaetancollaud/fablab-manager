package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.MembershipTypeEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>MembershipType</tt>.
* @author Fabien Vuilleumier
*/
public interface MembershipTypeService extends ReadWriteService<MembershipTypeEO> {

    public List<PriceMachineEO> getPrices(Integer id);

    public MembershipTypeEO getId(String name);

}
