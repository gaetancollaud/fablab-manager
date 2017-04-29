package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.MachineTypeRepository;
import net.collaud.fablab.manager.data.MachineTypeEO;
import net.collaud.fablab.manager.service.MachineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
public class MachineTypeServiceImpl extends AbstractServiceImpl implements MachineTypeService {

	@Autowired
	private MachineTypeRepository machineTypeDao;

	@Override
	public List<MachineTypeEO> findAll() {
		return new ArrayList(new HashSet(machineTypeDao.findAll()));
	}

	@Override
	public Optional<MachineTypeEO> getById(Long id) {
		return Optional.ofNullable(machineTypeDao.findOne(id));
	}
}
