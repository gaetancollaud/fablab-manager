package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.TrainingEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>Training</tt>.
* @author Fabien Vuilleumier
*/
public interface TrainingService extends ReadWriteService<TrainingEO>{

    public TrainingEO getId(String name);

}
