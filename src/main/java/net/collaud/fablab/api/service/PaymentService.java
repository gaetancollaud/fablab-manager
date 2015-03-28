package net.collaud.fablab.api.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.PaymentEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.exceptions.FablabException;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface PaymentService {

	UserEO addSubscriptionConfirmation(UserEO userSelected);

	UserEO addSubscriptionConfirmationForCurrentUser();

	PaymentEO addPayment(UserEO user, Date datePayment, float amount, String comment);

	UsageEO useMachine(UserEO user, MachineEO machine, Date startDate, int minutes, float additionalCost, String comment);

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

	void removeHistoryEntry(UserEO user, HistoryEntry entry);
}
