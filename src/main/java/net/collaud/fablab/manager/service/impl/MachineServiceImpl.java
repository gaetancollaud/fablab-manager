package net.collaud.fablab.manager.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.MachineRepository;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.MachineStatusEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.MachineService;
import net.collaud.fablab.manager.service.MachineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Secured({Roles.RESERVATION_USE, Roles.PAYMENT_MANAGE, Roles.MACHINE_VIEW, Roles.MACHINE_MANAGE})
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepository machineDao;

    @Autowired
    private MachineStatusService machineStatusService;

    @Override
    @Secured({Roles.RESERVATION_USE, Roles.PAYMENT_MANAGE, Roles.MACHINE_VIEW})
    public List<MachineEO> findAll() {
        return machineDao.findAll();
    }

    @Override
    @Secured({Roles.RESERVATION_USE, Roles.PAYMENT_MANAGE, Roles.MACHINE_VIEW})
    public Optional<MachineEO> getById(Integer id) {
        return machineDao.findOneDetails(id);
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public MachineEO findSimpleByCode(String code) {
        return machineDao.findSimpleByCode(code);
    }

    @Override
    @Secured(Roles.MACHINE_MANAGE)
    public MachineEO save(MachineEO machine) {
        if (machine.getId() == null) {
            machine.setId(0);
        }
        if (machine.getId() > 0) {
            MachineEO old = machineDao.findOne(machine.getId());
            old.setCode(machine.getCode());
            old.setName(machine.getName());
            old.setBuyPrice(machine.getBuyPrice());
            old.setAcquisitionDate(machine.getAcquisitionDate());
            old.setMachineType(machine.getMachineType());
            old.setMachineState(machine.getMachineState());
            old.setMachineStatus(machine.getMachineStatus());
            return machineDao.saveAndFlush(old);
        } else {
            return machineDao.saveAndFlush(machine);
        }
    }

    @Override
    @Secured(Roles.MACHINE_MANAGE)
    public void remove(Integer id) {
        MachineEO current = machineDao.findOne(id);
        current.setActive(false);
        machineDao.saveAndFlush(current);
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public List<MachineEO> getByStatusLabel(String label) {
        return machineDao.getByStatusLabel(label);
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW})
    public MachineStatusEO saveStatus(Integer machineId, Integer machineStatusId) {
        MachineEO machine = machineDao.findOne(machineId);
        MachineStatusEO machineStatus = machineStatusService.getById(machineStatusId).get();
        MachineEO old = machineDao.findOne(machine.getId());
        old.setMachineStatus(machineStatus);
        machineDao.saveAndFlush(old);
        return machineStatus;
    }
}
