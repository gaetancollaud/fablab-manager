package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.RevisionRepository;
import net.collaud.fablab.manager.data.RevisionEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>Revision</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.MACHINE_MANAGE})
public class RevisionServiceImpl implements RevisionService {

    @Autowired
    private RevisionRepository revisionDAO;

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public List<RevisionEO> findAll() {
        return new ArrayList(new HashSet(revisionDAO.findAll()));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public Optional<RevisionEO> getById(Integer id) {
        return revisionDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public RevisionEO save(RevisionEO revision) {
        if (revision.getId() == null) {
            revision.setId(0);
        }
        if (revision.getId() > 0) {
            RevisionEO old = revisionDAO.findOne(revision.getId());
            old.setRevisionDate(revision.getRevisionDate());
            old.setRevisionTime(revision.getRevisionTime());
            old.setNote(revision.getNote());
            old.setUser(revision.getUser());
            old.setCost(revision.getCost());
            old.setMachine(revision.getMachine());
            return revisionDAO.saveAndFlush(old);
        } else {
            return revisionDAO.saveAndFlush(revision);
        }
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public void remove(Integer id) {
        RevisionEO current = revisionDAO.findOne(id);
        current.setActive(false);
        revisionDAO.saveAndFlush(current);
    }

    @Override
     @Secured({Roles.MACHINE_MANAGE})
    public List<RevisionEO> listByMachine(Integer id) {
        return revisionDAO.listByMachine(id);
    }
    
}
