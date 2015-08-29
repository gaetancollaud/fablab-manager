package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.TicketEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *This is the Service interface for a <tt>Ticket</tt>.
* @author Fabien Vuilleumier
*/
public interface TicketService extends ReadWriteService<TicketEO> {

    List<TicketEO> listByMachine(Integer id);

}
