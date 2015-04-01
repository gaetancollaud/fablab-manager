package net.collaud.fablab.api.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.rest.v1.model.BaseModel;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import net.collaud.fablab.api.service.PaymentService;
import net.collaud.fablab.api.service.PriceService;
import net.collaud.fablab.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/payment")
@JavascriptAPIConstant("PAYMENT_API")
public class PaymentWS {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PriceService priceService;

	@RequestMapping(value = "{userId}/history", method = RequestMethod.GET)
	public BaseModel getallMembershipType(@PathVariable Integer userId) {
		return new DataModel(paymentService.getLastPaymentEntries(userId));
	}
	
	@RequestMapping(value = "machine_price", method = RequestMethod.GET)
	public BaseModel getAllPriceMachine(){
		final List<PriceMachineEO> allCurrentMachinePrices = priceService.getAllCurrentMachinePrices();
		return new DataModel(priceService.getAllCurrentMachinePrices());
	}

}
