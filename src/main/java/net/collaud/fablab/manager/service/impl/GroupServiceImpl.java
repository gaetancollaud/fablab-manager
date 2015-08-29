package net.collaud.fablab.manager.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.GroupRepository;
import net.collaud.fablab.manager.data.GroupEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>Group</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.ADMIN, Roles.GROUP_MANAGE})
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupDAO;

    @Override
    @Secured({Roles.ADMIN, Roles.GROUP_MANAGE})
    public List<GroupEO> findAll() {
        return groupDAO.findAll();
    }

    @Override
    @Secured({Roles.ADMIN, Roles.GROUP_MANAGE})
    public Optional<GroupEO> getById(Integer id) {
        Optional<GroupEO> g = groupDAO.findOneDetails(id);
        return g;
    }

    @Override
    @Secured({Roles.ADMIN, Roles.GROUP_MANAGE})
    public GroupEO save(GroupEO group) {
        if (group.getId() == null) {
            group.setId(0);
        }
        if (group.getId() > 0) {
            GroupEO old = groupDAO.findOneDetails(group.getId()).get();
            System.out.println("GROUP " + group);
            System.out.println("OLD (SAVE)" + old);
            old.setActive(group.isActive());
            old.setName(group.getName());
            old.setTechnicalname(group.getTechnicalname());
            old.setRoles(group.getRoles());
            System.out.println("OLD (SAVE bis)" + old.getRoles());
            return groupDAO.saveAndFlush(old);
        } else {
            return groupDAO.saveAndFlush(group);
        }
    }

    @Override
    @Secured({Roles.ADMIN, Roles.GROUP_MANAGE})
    public void remove(Integer id) {
        GroupEO current = groupDAO.findOne(id);
        current.setActive(false);
        groupDAO.saveAndFlush(current);
    }

    @Override
    @Secured({Roles.ADMIN, Roles.GROUP_MANAGE})
    public GroupEO getId(String technicalname) {
       return groupDAO.getId(technicalname);
    }
}
