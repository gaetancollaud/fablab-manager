package net.collaud.fablab.api.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.dao.PriceRepository;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.data.PriceRevisionEO;
import net.collaud.fablab.api.data.PriceRevisionEO_;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Slf4j
@Transactional
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;

	@Override
	public List<PriceMachineEO> getAllCurrentMachinePrices() {
		final List<PriceMachineEO> list = getLastPriceRevision().getPriceMachineList();
		Hibernate.initialize(list);
		return list;
	}

	@Override
	public PriceRevisionEO getLastPriceRevision() {
		final Page<PriceRevisionEO> all = priceRepository.findAll(new PageRequest(0, 1, Sort.Direction.DESC, PriceRevisionEO_.dateRevision.getName()));
		return all.getSize() > 0 ? all.getContent().get(0) : null;
	}
}
