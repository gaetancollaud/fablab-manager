package net.collaud.fablab.api.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.PaymentEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.data.virtual.HistoryEntryId;
import net.collaud.fablab.api.data.virtual.UserPaymentHistory;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.criteria.PeriodSearchCriteria;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface PaymentService {

	SubscriptionEO addSubscriptionConfirmation(Integer userId);

	SubscriptionEO addSubscriptionConfirmationForCurrentUser();

	PaymentEO addPayment(Integer userId, Date datePayment, double amount, String comment);

	UsageEO useMachine(Integer userId, Integer machineId, Date startDate, int minutes, double additionalCost, String comment);

	UserPaymentHistory getLastPaymentEntries(Integer userId);

	List<HistoryEntry> getPaymentEntries(PeriodSearchCriteria search);

	List<HistoryEntry> getPaymentEntriesForCurrentUser();

	HistoryEntryId removeHistoryEntry(HistoryEntryId entry);
}
