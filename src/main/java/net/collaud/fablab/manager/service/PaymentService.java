package net.collaud.fablab.manager.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.audit.Audit;
import net.collaud.fablab.manager.data.PaymentEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.data.virtual.HistoryEntry;
import net.collaud.fablab.manager.data.virtual.HistoryEntryId;
import net.collaud.fablab.manager.data.virtual.UserPaymentHistory;
import net.collaud.fablab.manager.rest.v1.criteria.PeriodSearchCriteria;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface PaymentService {
	
	@Audit(object = AuditObject.SUBSCRIPTION, action = AuditAction.INSERT)
	SubscriptionEO addSubscription(Long userId, Date dateSubscriptionStart, Date datePayment, String comment, boolean paidDirectly);

	@Audit(object = AuditObject.PAYMENT, action = AuditAction.INSERT)
	PaymentEO addPayment(Long userId, Date datePayment, double amount, String comment);

	@Audit(object = AuditObject.USAGE, action = AuditAction.INSERT)
	UsageEO useMachine(Long userId, Long machineId, Date startDate, int minutes,
			double additionalCost, String comment, boolean paidDirectly);

	UserPaymentHistory getLastPaymentEntries(Long userId);

	List<HistoryEntry> getPaymentEntries(PeriodSearchCriteria search);

	HistoryEntryId removeHistoryEntry(HistoryEntryId entry);
}
