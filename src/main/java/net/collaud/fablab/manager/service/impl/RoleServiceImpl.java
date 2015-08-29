package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.RoleRepository;
import net.collaud.fablab.manager.data.RoleEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>Role</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.ADMIN})
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleDAO;

    @Override
    @Secured({Roles.ADMIN})
    public List<RoleEO> findAll() {
        return new ArrayList(new HashSet(roleDAO.findAll()));
    }

    @Override
    @Secured({Roles.ADMIN})
    public Optional<RoleEO> getById(Integer id) {
        return Optional.ofNullable(roleDAO.findOne(id));
    }
}
