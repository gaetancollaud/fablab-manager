package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.EventModuleRepository;
import net.collaud.fablab.manager.data.EventModuleEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.EventModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>EventModule</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.EVENT_VIEW})
public class EventModuleServiceImpl implements EventModuleService {

    @Autowired
    private EventModuleRepository eventModuleDAO;

    @Override
    @Secured({Roles.EVENT_VIEW})
    public List<EventModuleEO> findAll() {
        return new ArrayList(new HashSet(eventModuleDAO.findAll()));
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public Optional<EventModuleEO> getById(Integer id) {
        return eventModuleDAO.findOneDetails(id);
    }

     @Override
    @Secured({Roles.EVENT_VIEW})
    public EventModuleEO save(EventModuleEO eventModule) {
        if (eventModule.getId() == null) {
            eventModule.setId(0);
        }
        if (eventModule.getId() > 0) {
            EventModuleEO old = eventModuleDAO.findOne(eventModule.getId());
            old.setName(eventModule.getName());
            old.setMachineType(eventModule.getMachineType());
            old.setDescription(eventModule.getDescription());
            old.setPrerequisites(eventModule.getPrerequisites());
            return eventModuleDAO.saveAndFlush(old);
        } else {
            return eventModuleDAO.saveAndFlush(eventModule);
        }
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public void remove(Integer id) {
        EventModuleEO current = eventModuleDAO.findOne(id);
        current.setActive(false);
        eventModuleDAO.saveAndFlush(current);
    }

    @Override
    public EventModuleEO getId(String name) {
        return eventModuleDAO.getId(name);
    }
}

