package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.TicketStatusRepository;
import net.collaud.fablab.manager.data.TicketStatusEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>TicketStatus</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
public class TicketStatusServiceImpl implements TicketStatusService {

    @Autowired
    private TicketStatusRepository ticketStatusDAO;

    @Override
    @Secured(Roles.TICKET_VIEW)
    public List<TicketStatusEO> findAll() {
        return new ArrayList(new HashSet(ticketStatusDAO.findAll()));
    }

    @Override
    @Secured(Roles.TICKET_VIEW)
    public Optional<TicketStatusEO> getById(Integer id) {
        return Optional.ofNullable(ticketStatusDAO.findOne(id));
    }

    @Override
    @Secured(Roles.TICKET_MANAGE)
    public TicketStatusEO save(TicketStatusEO ticketStatus) {
        if (ticketStatus.getId() == null) {
            ticketStatus.setId(0);
        }
        if (ticketStatus.getId() > 0) {
            TicketStatusEO old = ticketStatusDAO.findOne(ticketStatus.getId());
            old.setLabel(ticketStatus.getLabel());
            return ticketStatusDAO.saveAndFlush(old);
        } else {
            return ticketStatusDAO.saveAndFlush(ticketStatus);
        }
    }

    @Override
    @Secured(Roles.TICKET_MANAGE)
    public void remove(Integer id) {
        TicketStatusEO current = ticketStatusDAO.findOne(id);
        current.setActive(false);
        ticketStatusDAO.saveAndFlush(current);
    }

    @Override
    @Secured(Roles.TICKET_VIEW) 
    public TicketStatusEO findByLabel(String label) {
        return ticketStatusDAO.findByLabel(label);
    }
}
