package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.data.PriceRevisionEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface PriceService {

	List<PriceMachineEO> getAllCurrentMachinePrices();
	
	PriceRevisionEO getLastPriceRevision();
}
