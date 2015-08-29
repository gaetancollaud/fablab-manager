package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.MachineStatusRepository;
import net.collaud.fablab.manager.data.MachineStatusEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.MachineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>MachineStatus</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.MACHINE_VIEW})
public class MachineStatusServiceImpl implements MachineStatusService {

    @Autowired
    private MachineStatusRepository machineStatusDAO;

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public List<MachineStatusEO> findAll() {
        return new ArrayList(new HashSet(machineStatusDAO.findAll()));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public Optional<MachineStatusEO> getById(Integer id) {
        return Optional.ofNullable(machineStatusDAO.findOne(id));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public MachineStatusEO save(MachineStatusEO machineStatus) {
        if (machineStatus.getId() == null) {
            machineStatus.setId(0);
        }
        if (machineStatus.getId() > 0) {
            MachineStatusEO old = machineStatusDAO.findOne(machineStatus.getId());
            old.setLabel(machineStatus.getLabel());
            old.setColor(machineStatus.getColor());
            old.setMachineList(machineStatus.getMachineList());
            return machineStatusDAO.saveAndFlush(old);
        } else {
            return machineStatusDAO.saveAndFlush(machineStatus);
        }
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public void remove(Integer id) {
        MachineStatusEO current = machineStatusDAO.findOne(id);
        current.setActive(false);
        machineStatusDAO.saveAndFlush(current);
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public MachineStatusEO getByLabel(String label) {
        return machineStatusDAO.getByLabel(label);
    }
}
