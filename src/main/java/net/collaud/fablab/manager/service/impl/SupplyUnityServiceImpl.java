package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.SupplyUnityRepository;
import net.collaud.fablab.manager.data.SupplyUnityEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.SupplyUnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>SupplyUnity</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.SUPPLY_MANAGE})
public class SupplyUnityServiceImpl implements SupplyUnityService {

    @Autowired
    private SupplyUnityRepository supplyUnityDAO;

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public List<SupplyUnityEO> findAll() {
        return new ArrayList(new HashSet(supplyUnityDAO.findAll()));
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public Optional<SupplyUnityEO> getById(Integer id) {
        return Optional.ofNullable(supplyUnityDAO.findOne(id));
    }

     @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public SupplyUnityEO save(SupplyUnityEO supplyUnity) {
        if (supplyUnity.getId() == null) {
            supplyUnity.setId(0);
        }
        if (supplyUnity.getId() > 0) {
            SupplyUnityEO old = supplyUnityDAO.findOne(supplyUnity.getId());
            old.setLabel(supplyUnity.getLabel());
            old.setFloating(supplyUnity.isFloating());
            old.setSupplyList(supplyUnity.getSupplyList());
            old.setActive(supplyUnity.isActive());
            return supplyUnityDAO.saveAndFlush(old);
        } else {
            return supplyUnityDAO.saveAndFlush(supplyUnity);
        }
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public void remove(Integer id) {
        SupplyUnityEO current = supplyUnityDAO.findOne(id);
        current.setActive(false);
        supplyUnityDAO.saveAndFlush(current);
    }
}

