package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.EventModuleEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>EventModule</tt>.
* @author Fabien Vuilleumier
*/
public interface EventModuleService extends ReadWriteService<EventModuleEO>{

    EventModuleEO getId(String name);

}
