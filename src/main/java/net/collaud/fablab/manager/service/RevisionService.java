package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.RevisionEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *This is the Service interface for a <tt>Revision</tt>.
* @author Fabien Vuilleumier
*/
public interface RevisionService extends ReadWriteService<RevisionEO> {

    List<RevisionEO> listByMachine(Integer id);

}
