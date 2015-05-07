package net.collaud.fablab.api.rest.v1;

import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.rest.v1.model.BaseModel;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import net.collaud.fablab.api.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/price")
@JavascriptAPIConstant("PRICE_API")
public class PriceWS {

	@Autowired
	private PriceService priceService;

	@RequestMapping(value = "machine", method = RequestMethod.GET)
	public BaseModel getAllPriceMachine() {
		return new DataModel(priceService.getAllCurrentMachinePrices());
	}

}
