package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.EventEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>Event</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface EventService extends ReadWriteService<EventEO> {

    EventEO getId(String title);
}
