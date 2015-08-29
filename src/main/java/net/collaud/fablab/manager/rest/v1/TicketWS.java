package net.collaud.fablab.manager.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.TicketEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Ticket</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/ticket")
@JavascriptAPIConstant("TICKET_API")
public class TicketWS extends ReadWriteRestWebservice<TicketEO, TicketService>{

    @Autowired
    private TicketService ticketService;

    @PostConstruct
    public void postConstruct() {
        super.setService(ticketService);
    }

    @RequestMapping(value = "listByMachine", method = RequestMethod.GET)
    public List<TicketEO> listByMachine(@RequestParam(value = "id") Integer id) {
        return ticketService.listByMachine(id);
    }
}
