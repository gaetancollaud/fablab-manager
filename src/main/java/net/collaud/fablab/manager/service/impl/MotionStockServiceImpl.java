package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.MotionStockRepository;
import net.collaud.fablab.manager.data.MotionStockEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.MotionStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>MotionStock</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.SUPPLY_MANAGE})
public class MotionStockServiceImpl implements MotionStockService {

    @Autowired
    private MotionStockRepository motionStockDAO;

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public List<MotionStockEO> findAll() {
        return new ArrayList(new HashSet(motionStockDAO.findAll()));
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public Optional<MotionStockEO> getById(Integer id) {
        return motionStockDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public MotionStockEO save(MotionStockEO ms) {
        if (ms.getId() == null) {
            ms.setId(0);
        }
        if (ms.getId() > 0) {
            MotionStockEO old = motionStockDAO.findOne(ms.getId());
            old.setMotionDate(ms.getMotionDate());
            old.setQuantity(ms.getQuantity());
            old.setIo(ms.getIo());
            old.setActive(ms.isActive());
            old.setSupply(ms.getSupply());
            old.setUser(ms.getUser());
            return motionStockDAO.saveAndFlush(old);
        } else {
            return motionStockDAO.saveAndFlush(ms);
        }
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public void remove(Integer id) {
        MotionStockEO current = motionStockDAO.findOne(id);
        current.setActive(false);
        motionStockDAO.saveAndFlush(current);
    }
}
