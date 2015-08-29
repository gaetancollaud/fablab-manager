package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.EventTypeRepository;
import net.collaud.fablab.manager.data.EventTypeEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>EventType</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.EVENT_VIEW})
public class EventTypeServiceImpl implements EventTypeService {

    @Autowired
    private EventTypeRepository eventTypeDAO;

    @Override
    @Secured({Roles.EVENT_VIEW})
    public List<EventTypeEO> findAll() {
        return new ArrayList(new HashSet(eventTypeDAO.findAll()));
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public Optional<EventTypeEO> getById(Integer id) {
        return Optional.ofNullable(eventTypeDAO.findOne(id));
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public EventTypeEO save(EventTypeEO eventType) {
        if (eventType.getId() == null) {
            eventType.setId(0);
        }
        if (eventType.getId() > 0) {
            EventTypeEO old = eventTypeDAO.findOne(eventType.getId());
            old.setLabel(eventType.getLabel());
            return eventTypeDAO.saveAndFlush(old);
        } else {
            return eventTypeDAO.saveAndFlush(eventType);
        }
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public void remove(Integer id) {
        EventTypeEO current = eventTypeDAO.findOne(id);
        current.setActive(false);
        eventTypeDAO.saveAndFlush(current);
    }
}
