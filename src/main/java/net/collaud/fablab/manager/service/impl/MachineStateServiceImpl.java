package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.MachineStateRepository;
import net.collaud.fablab.manager.data.MachineStateEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.MachineStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>MachineState</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.MACHINE_VIEW})
public class MachineStateServiceImpl implements MachineStateService {

    @Autowired
    private MachineStateRepository machineStateDAO;

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public List<MachineStateEO> findAll() {
        return new ArrayList(new HashSet(machineStateDAO.findAll()));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public Optional<MachineStateEO> getById(Integer id) {
        return Optional.ofNullable(machineStateDAO.findOne(id));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public MachineStateEO save(MachineStateEO machineState) {
        if (machineState.getId() == null) {
            machineState.setId(0);
        }
        if (machineState.getId() > 0) {
            MachineStateEO old = machineStateDAO.findOne(machineState.getId());
            old.setLabel(machineState.getLabel());
            old.setMachineList(machineState.getMachineList());
            return machineStateDAO.saveAndFlush(old);
        } else {
            return machineStateDAO.saveAndFlush(machineState);
        }
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public void remove(Integer id) {
        MachineStateEO current = machineStateDAO.findOne(id);
        current.setActive(false);
        machineStateDAO.saveAndFlush(current);
    }
}
