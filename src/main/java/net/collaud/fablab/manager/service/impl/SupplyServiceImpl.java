package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.SupplyRepository;
import net.collaud.fablab.manager.data.SupplyEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>Supply</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.SUPPLY_VIEW})
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyDAO;

    @Override
    @Secured({Roles.ADMIN})
    public List<SupplyEO> findAll() {
        return new ArrayList(new HashSet(supplyDAO.findAll()));
    }

    @Override
    @Secured({Roles.ADMIN})
    public Optional<SupplyEO> getById(Integer id) {
        return supplyDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.ADMIN})
    public SupplyEO save(SupplyEO supply) {
        if (supply.getId() == null) {
            supply.setId(0);
        }
        if (supply.getId() > 0) {
            SupplyEO old = supplyDAO.findOne(supply.getId());
            old.setCode(supply.getCode());
            old.setLabel(supply.getLabel());
            old.setSellingPrice(supply.getSellingPrice());
            old.setUnityBuyingPrice(supply.getUnityBuyingPrice());
            old.setOrderAddress(supply.getOrderAddress());
            old.setSupplyType(supply.getSupplyType());
            old.setQuantityStock(supply.getQuantityStock());
            old.setSupplyUnity(supply.getSupplyUnity());
            old.setCreationUser(supply.getCreationUser());
            old.setActive(supply.isActive());
            return supplyDAO.saveAndFlush(old);
        } else {
            return supplyDAO.saveAndFlush(supply);
        }
    }

    @Override
    @Secured({Roles.ADMIN})
    public void remove(Integer id) {
        SupplyEO current = supplyDAO.findOne(id);
        current.setActive(false);
        supplyDAO.saveAndFlush(current);
    }

    @Override
    public List<SupplyEO> stock() {
        return supplyDAO.stock();
    }

    @Override
    public void addQuantity(Integer id, Float quantity) {
        SupplyEO supply = supplyDAO.findOne(id);
        supply.setQuantityStock(supply.getQuantityStock() + quantity);
        supplyDAO.saveAndFlush(supply);
    }
}
