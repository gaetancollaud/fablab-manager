package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.TicketStatusEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>TicketStatus</tt>.
* @author Fabien Vuilleumier
*/
public interface TicketStatusService extends ReadWriteService<TicketStatusEO> {

    public TicketStatusEO findByLabel(String label);

}
