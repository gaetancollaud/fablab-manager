package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.TrainingEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Training</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/training")
@JavascriptAPIConstant("TRAINING_API")
public class TrainingWS extends ReadWriteRestWebservice<TrainingEO, TrainingService> {

    @Autowired
    private TrainingService trainingService;

    @PostConstruct
    public void postConstruct() {
        super.setService(trainingService);
    }

    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public TrainingEO getId(@RequestParam(value = "name") String name) {
        return trainingService.getId(name);
    }

}
