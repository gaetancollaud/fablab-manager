package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.EventPersonRepository;
import net.collaud.fablab.manager.data.EventModuleEO;
import net.collaud.fablab.manager.data.EventPersonEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.EventPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>EventPerson</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.EVENT_VIEW})
public class EventPersonServiceImpl implements EventPersonService {

    @Autowired
    private EventPersonRepository eventPersonDAO;

    @Override
    @Secured({Roles.EVENT_VIEW})
    public List<EventPersonEO> findAll() {
        return new ArrayList(new HashSet(eventPersonDAO.findAll()));
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public Optional<EventPersonEO> getById(Integer id) {
        return eventPersonDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public EventPersonEO save(EventPersonEO eventPerson) {
        if (eventPerson.getId() == null) {
            eventPerson.setId(0);
        }
        if (eventPerson.getId() > 0) {
            EventPersonEO old = eventPersonDAO.findOneDetails(eventPerson.getId()).get();
            old.setLastname(eventPerson.getLastname());
            old.setFirstname(eventPerson.getFirstname());
            old.setEmail(eventPerson.getEmail());
            old.setAcquiredModules(eventPerson.getAcquiredModules());
            return eventPersonDAO.saveAndFlush(old);
        } else {
            return eventPersonDAO.saveAndFlush(eventPerson);
        }
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public void remove(Integer id) {
        EventPersonEO current = eventPersonDAO.findOne(id);
        current.setActive(false);
        eventPersonDAO.saveAndFlush(current);
    }

    @Override
    public EventPersonEO getId(String email) {
        return eventPersonDAO.getId(email);
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public List<Integer> failedModules(Integer eventPersonId, List<Integer> eventModuleId) {
        List<Integer> res = new ArrayList<>();
        EventPersonEO ep = getById(eventPersonId).get();
        if (ep != null) {
            for (EventModuleEO personAcquiredModules : ep.getAcquiredModules()) {
                for (Integer moduleId : eventModuleId) {
                    if (!personAcquiredModules.getId().equals(moduleId)) {
                        res.add(eventPersonId);
                    }
                }
            }
        }
        return res;
    }
}
