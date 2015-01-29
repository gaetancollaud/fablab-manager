package net.collaud.fablab.api.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.MachineRepository;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.MachineService;
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
@Secured({RolesHelper.ROLE_ADMIN, RolesHelper.ROLE_MANAGE_MACHINE})
public class MachineServiceImpl extends AbstractServiceImpl implements MachineService {

	@Autowired
	private MachineRepository machineDao;

	@Override
	@Secured({RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_PAYMENT})
	public List<MachineEO> findAll() {
		return machineDao.findAll();
	}

	@Override
	@Secured({RolesHelper.ROLE_USE_RESERVATION, RolesHelper.ROLE_MANAGE_PAYMENT})
	public Optional<MachineEO> getById(Integer id) {
		return Optional.ofNullable(machineDao.findOne(id));
	}
}
