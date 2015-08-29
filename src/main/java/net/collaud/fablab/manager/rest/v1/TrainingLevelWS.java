package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.TrainingLevelEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.TrainingLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>TrainingLevel</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/trainingLevel")
@JavascriptAPIConstant("TRAINING_LEVEL_API")
public class TrainingLevelWS extends ReadWriteRestWebservice<TrainingLevelEO, TrainingLevelService> {

    @Autowired
    private TrainingLevelService trainingLevelService;

    @PostConstruct
    public void postConstruct() {
        super.setService(trainingLevelService);
    }
}
