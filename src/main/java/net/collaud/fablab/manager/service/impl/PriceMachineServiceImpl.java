package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.PriceMachineRepository;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.PriceMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>PriceMachine</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.MACHINE_MANAGE})
public class PriceMachineServiceImpl implements PriceMachineService {

    @Autowired
    private PriceMachineRepository priceMachineDAO;

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public List<PriceMachineEO> findAll() {
        return new ArrayList(new HashSet(priceMachineDAO.findAll()));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public Optional<PriceMachineEO> getById(Integer id) {
        return priceMachineDAO.findOneDetails(id);
    }

     @Override
    @Secured({Roles.MACHINE_MANAGE})
    public PriceMachineEO save(PriceMachineEO priceMachine) {
        if (priceMachine.getId() == null) {
            priceMachine.setId(0);
        }
        if (priceMachine.getId() > 0) {
            PriceMachineEO old = priceMachineDAO.findOne(priceMachine.getId());
            old.setPrice(priceMachine.getPrice());
            old.setMachineType(priceMachine.getMachineType());
            old.setMembershipType(priceMachine.getMembershipType());
            return priceMachineDAO.saveAndFlush(old);
        } else {
            return priceMachineDAO.saveAndFlush(priceMachine);
        }
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public void remove(Integer id) {
        priceMachineDAO.delete(id);
    }

    @Override
    public PriceMachineEO getPriceMachine(Integer machineTypeId, Integer membershipTypeId) {
        return priceMachineDAO.getPriceMachine(machineTypeId, membershipTypeId);
    }

    @Override
    public List<PriceMachineEO> getMachineType(Integer machineTypeId) {
         return priceMachineDAO.getMachineType(machineTypeId);
    }

    @Override
    public List<PriceMachineEO> getMembershipType(Integer membershipTypeId) {
         return priceMachineDAO.getMembershipType(membershipTypeId);
    }
}

