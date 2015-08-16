package net.collaud.fablab.manager.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.PaymentEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.virtual.HistoryEntry;
import net.collaud.fablab.manager.data.virtual.HistoryEntryId;
import net.collaud.fablab.manager.data.virtual.UserPaymentHistory;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.rest.v1.criteria.PeriodSearchCriteria;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface PaymentService {

	SubscriptionEO addSubscriptionConfirmation(Integer userId);

	SubscriptionEO addSubscriptionConfirmationForCurrentUser();

	PaymentEO addPayment(Integer userId, Date datePayment, double amount, String comment);

	UsageEO useMachine(Integer userId, Integer machineId, Date startDate, int minutes,
			double additionalCost, String comment, boolean paidDirectly);

	UserPaymentHistory getLastPaymentEntries(Integer userId);

	List<HistoryEntry> getPaymentEntries(PeriodSearchCriteria search);

	HistoryEntryId removeHistoryEntry(HistoryEntryId entry);
}
