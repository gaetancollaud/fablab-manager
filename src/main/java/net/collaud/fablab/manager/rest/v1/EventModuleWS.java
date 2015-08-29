package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.EventModuleEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.EventModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>EventModule</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/eventModule")
@JavascriptAPIConstant("EVENT_MODULE_API")
public class EventModuleWS extends ReadWriteRestWebservice<EventModuleEO, EventModuleService>{

    @Autowired
    private EventModuleService eventModuleService;

    @PostConstruct
    public void postConstruct(){
        super.setService(eventModuleService);
    }

    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public EventModuleEO getId(@RequestParam(value = "name") String name) {
        return eventModuleService.getId(name);
    }
}

