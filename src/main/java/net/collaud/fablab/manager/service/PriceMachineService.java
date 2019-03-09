package net.collaud.fablab.manager.service;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.PriceMachineRepository;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.data.PriceMachineEOPK;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.global.ReadWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Slf4j
@Service
public class PriceMachineService {

	@Autowired
	private PriceMachineRepository priceMachineRepository;

	@Secured({Roles.MACHINE_MANAGE})
	public PriceMachineEO save(PriceMachineEO entity) {
		return priceMachineRepository.save(entity);
	}

	@Secured({Roles.MACHINE_MANAGE, Roles.MACHINE_VIEW, Roles.PAYMENT_VIEW, Roles.PAYMENT_MANAGE})
	public List<PriceMachineEO> findAll() {
		return priceMachineRepository.findAll();
	}

}
