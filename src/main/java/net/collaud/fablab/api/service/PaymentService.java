package net.collaud.fablab.api.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.PaymentEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.data.virtual.HistoryEntryId;
import net.collaud.fablab.api.exceptions.FablabException;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface PaymentService {

	UserEO addSubscriptionConfirmation(UserEO userSelected);

	UserEO addSubscriptionConfirmationForCurrentUser();

	PaymentEO addPayment(Integer userId, Date datePayment, double amount, String comment);

	UsageEO useMachine(Integer userId, Integer machineId, Date startDate, int minutes, double additionalCost, String comment);

	List<HistoryEntry> getLastPaymentEntries(Integer userId);

	/**
	 * Get the list of entry for accounting.
	 *
	 * @param dateBefore entries before this date
	 * @param dateAfter entries after this date
	 * @return
	 * @throws FablabException
	 */
	List<HistoryEntry> getPaymentEntries(Date dateBefore, Date dateAfter);

	List<HistoryEntry> getPaymentEntriesForCurrentUser();

	HistoryEntryId removeHistoryEntry(HistoryEntryId entry);
}
