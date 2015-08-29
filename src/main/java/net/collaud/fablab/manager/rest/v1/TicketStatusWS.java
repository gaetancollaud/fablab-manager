package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.TicketStatusEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>TicketStatus</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/ticketStatus")
@JavascriptAPIConstant("TICKET_STATUS_API")
public class TicketStatusWS extends ReadWriteRestWebservice<TicketStatusEO, TicketStatusService>{

    @Autowired
    private TicketStatusService ticketStatusService;

    @PostConstruct
    public void postConstruct() {
        super.setService(ticketStatusService);
    }
    
    @RequestMapping(value = "findByLabel", method = RequestMethod.GET)
    public TicketStatusEO findByLabel(@RequestParam(value = "label") String label) {
        return ticketStatusService.findByLabel(label);
    }
}
