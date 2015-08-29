package net.collaud.fablab.manager.rest.v1;

import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.virtual.HistoryEntryId;
import net.collaud.fablab.manager.rest.v1.data.AddSubscriptionTO;
import net.collaud.fablab.manager.rest.v1.model.BaseModel;
import net.collaud.fablab.manager.rest.v1.model.DataModel;
import net.collaud.fablab.manager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "{userId}/history", method = RequestMethod.GET)
	public BaseModel getallMembershipType(@PathVariable Integer userId) {
		return new DataModel(paymentService.getLastPaymentEntries(userId));
	}

	@RequestMapping(value = "add_usage", method = RequestMethod.POST)
	public BaseModel addUsage(@RequestBody @Validated UsageEO usage) {
		return new DataModel(paymentService.useMachine(usage.getUser().getId(),
				usage.getMachine().getId(), usage.getDateStart(), usage.getMinutes(),
				usage.getAdditionalCost(), usage.getNote(), true));
	}

	/*@RequestMapping(value = "add_payment", method = RequestMethod.POST)
	public BaseModel addPayment(@RequestBody @Validated PaymentUserEO payment) {
		return new DataModel(paymentService.addPayment(payment.getUser().getId()
				, payment.getDatePayment(), payment.getTotal(), payment.getNote()));
	}*/

	@RequestMapping(value = "add_subscription", method = RequestMethod.POST)
	public BaseModel addSubscription(@RequestBody @Validated AddSubscriptionTO payment) {
		return new DataModel(paymentService.addSubscription(
				payment.getUserId(),
				payment.getStartDate(),
				payment.getPaymentDate(),
				payment.getComment(),
				payment.isPaidDirecly()));
	}

	@RequestMapping(value = "delete_history", method = RequestMethod.POST)
	public BaseModel removeHistory(@RequestBody @Validated HistoryEntryId id) {
		return new DataModel(paymentService.removeHistoryEntry(id));
	}

//	@RequestMapping(value = "subscription/confirm/{id}", method = RequestMethod.GET)
//	public BaseModel confirmSubscription(@PathVariable Integer id) {
//		return new DataModel(paymentService.addSubscriptionConfirmation(id));
//	}

//	@RequestMapping(value = "subscription/confirm", method = RequestMethod.GET)
//	public BaseModel confirmSubscriptionForCurrentUser() {
//		return new DataModel(paymentService.addSubscriptionConfirmationForCurrentUser());
//	}
        
        @RequestMapping(value = "getPrice", method = RequestMethod.GET)
        public Float getPrice(@RequestParam("machineTypeId") Integer machineTypeId, 
                @RequestParam("userId") Integer userId){
            return paymentService.getPrice(machineTypeId, userId);
        }

}
