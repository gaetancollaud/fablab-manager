package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.EventEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Event</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/event")
@JavascriptAPIConstant("EVENT_API")
public class EventWS extends ReadWriteRestWebservice<EventEO, EventService> {

    @Autowired
    private EventService eventService;

    @PostConstruct
    public void postConstruct() {
        super.setService(eventService);
    }

    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public EventEO getId(@RequestParam(value = "title") String title) {
        return eventService.getId(title);
    }
}
