package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.GroupEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *This is the Service interface for a <tt>Group</tt>.
* @author Fabien Vuilleumier
*/
public interface GroupService extends ReadWriteService<GroupEO>{

    GroupEO getId(String technicalname);

}
