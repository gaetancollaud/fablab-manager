package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.MachineRepository;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
public class MachineServiceImpl extends AbstractServiceImpl implements MachineService {

	@Autowired
	private MachineRepository machineDao;

	@Override
	public List<MachineEO> findAll() {
		return new ArrayList(new HashSet(machineDao.findAll()));
	}

	@Override
	public Optional<MachineEO> getById(Long id) {
		return Optional.ofNullable(machineDao.findOneWithDescription(id));
	}
}
