package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.EventPersonEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>EventPerson</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface EventPersonService extends ReadWriteService<EventPersonEO> {

    EventPersonEO getId(String email);

    List<Integer> failedModules(Integer eventPersonId, List<Integer> eventModuleId);
}
