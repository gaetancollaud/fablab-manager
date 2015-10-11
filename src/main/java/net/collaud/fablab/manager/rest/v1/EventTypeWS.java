package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.EventTypeEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>EventType</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/eventType")
@JavascriptAPIConstant("EVENT_TYPE_API")
public class EventTypeWS extends ReadWriteRestWebservice<EventTypeEO, EventTypeService>{

    @Autowired
    private EventTypeService eventTypeService;

    @PostConstruct
    public void postConstruct(){
        super.setService(eventTypeService);
    }
}
