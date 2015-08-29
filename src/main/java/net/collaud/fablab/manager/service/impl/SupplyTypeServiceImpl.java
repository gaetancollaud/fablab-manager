package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.SupplyTypeRepository;
import net.collaud.fablab.manager.data.SupplyTypeEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.SupplyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>SupplyType</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.SUPPLY_MANAGE})
public class SupplyTypeServiceImpl implements SupplyTypeService {

    @Autowired
    private SupplyTypeRepository supplyTypeDAO;

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public List<SupplyTypeEO> findAll() {
        return new ArrayList(new HashSet(supplyTypeDAO.findAll()));
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public Optional<SupplyTypeEO> getById(Integer id) {
        return Optional.ofNullable(supplyTypeDAO.findOne(id));
    }

     @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public SupplyTypeEO save(SupplyTypeEO supplyType) {
        if (supplyType.getId() == null) {
            supplyType.setId(0);
        }
        if (supplyType.getId() > 0) {
            SupplyTypeEO old = supplyTypeDAO.findOne(supplyType.getId());
            old.setLabel(supplyType.getLabel());
            old.setSupplyList(supplyType.getSupplyList());
            old.setActive(supplyType.isActive());
            return supplyTypeDAO.saveAndFlush(old);
        } else {
            return supplyTypeDAO.saveAndFlush(supplyType);
        }
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public void remove(Integer id) {
        SupplyTypeEO current = supplyTypeDAO.findOne(id);
        current.setActive(false);
        supplyTypeDAO.saveAndFlush(current);
    }
}

