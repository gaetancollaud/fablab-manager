package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.UsageRepository;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>Usage</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.PAYMENT_VIEW})
public class UsageServiceImpl implements UsageService {

    @Autowired
    private UsageRepository usageDAO;

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public List<UsageEO> findAll() {
        return new ArrayList(new HashSet(usageDAO.findAll()));
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public Optional<UsageEO> getById(Integer id) {
        return usageDAO.findOneDetails(id);
    }

     @Override
    @Secured({Roles.PAYMENT_VIEW})
    public UsageEO save(UsageEO usage) {
        if (usage.getId() == null) {
            usage.setId(0);
        }
        if (usage.getId() > 0) {
            UsageEO old = usageDAO.findOne(usage.getId());
            old.setDateStart(usage.getDateStart());
            old.setPricePerHour(usage.getPricePerHour());
            old.setDiscount(usage.getDiscount());
            old.setDiscountPercent(usage.isDiscountPercent());
            old.setMinutes(usage.getMinutes());
            old.setAdditionalCost(usage.getAdditionalCost());
            old.setTotal(usage.getTotal());
            old.setNote(usage.getNote());
            old.setUser(usage.getUser());
            old.setCashier(usage.getCashier());
            old.setMachine(usage.getMachine());
            old.setMembershipType(usage.getMembershipType());
            old.setActive(usage.isActive());
            return usageDAO.saveAndFlush(old);
        } else {
            return usageDAO.saveAndFlush(usage);
        }
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public void remove(Integer id) {
        UsageEO current = usageDAO.findOne(id);
        current.setActive(false);
        usageDAO.saveAndFlush(current);
    }
}

