package net.collaud.fablab.manager.dao.impl;

import com.querydsl.jpa.impl.JPAQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.MachineRepositoryCustom;
import net.collaud.fablab.manager.dao.projection.MachineProjection;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.data.QMachineEO;
import net.collaud.fablab.manager.data.QMachineTypeEO;
import net.collaud.fablab.manager.data.QPriceMachineEO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
@Transactional
@Slf4j
public class MachineRepositoryImpl implements MachineRepositoryCustom {

	private final QMachineEO machine = QMachineEO.machineEO;
	private final QMachineTypeEO machineType = QMachineTypeEO.machineTypeEO;
	private final QPriceMachineEO priceMachine = QPriceMachineEO.priceMachineEO;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<MachineEO> findAll() {
		final List<MachineEO> machines = new JPAQuery<>(entityManager)
				.select(MachineProjection.projectionWithoutContent(machine))
				.from(machine)
				.innerJoin(machine.machineType, machineType)
				.orderBy(machine.id.desc())
				.fetch();

		final Set<Long> machineTypeIds = machines.stream()
				.map(m -> m.getMachineType().getId())
				.collect(Collectors.toSet());

		final Map<Long, List<PriceMachineEO>> priceByMachineType = new JPAQuery<>(entityManager)
				.select(priceMachine)
				.from(priceMachine)
				.where(priceMachine.machineTypeId.in(machineTypeIds))
				.fetch()
				.stream()
				.collect(Collectors.groupingBy(PriceMachineEO::getMachineTypeId));
		
		machines.forEach(m -> m
				.getMachineType()
				.setPriceList(new HashSet<>(priceByMachineType.get(m.getMachineType().getId()))));

		return machines;
	}

	@Override
	public MachineEO findOneWithDescription(Long machineId) {
		final MachineEO machineEO = new JPAQuery<>(entityManager)
				.select(MachineProjection.projectionWithContent(machine))
				.from(machine)
				.innerJoin(machine.machineType, machineType)
				.where(machine.id.eq(machineId))
				.fetchOne();
		
		final List<PriceMachineEO> prices = new JPAQuery<>(entityManager)
				.select(priceMachine)
				.from(priceMachine)
				.where(priceMachine.machineTypeId.eq(machineEO.getMachineType().getId()))
				.fetch();
		
		machineEO.getMachineType().setPriceList(new HashSet<>(prices));
		
		return machineEO;
	}

}
