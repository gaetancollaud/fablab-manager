package net.collaud.fablab.manager.data.virtual;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.PaymentEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.HistoryEntryType;
import net.collaud.fablab.manager.export.CsvExport;
import net.collaud.fablab.manager.export.CsvField;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "type"})
@CsvExport(fileName = "accounting")
public class HistoryEntry implements Comparable<HistoryEntry> {

	private final int id;
	@CsvField(headerName = "type")
	private final HistoryEntryType type;
	@CsvField(headerName = "date")
	private final Date date;
	@CsvField(headerName = "amount")
	private final double amount;
	@CsvField(headerName = "detail")
	private final String detail;
	@CsvField(headerName = "comment")
	private final String comment;
	@CsvField(headerName = "user")
	private final HistoryEntryUser user;

	public HistoryEntry(PaymentEO payment) {
		type = HistoryEntryType.PAYMENT;
		id = payment.getId();
		date = payment.getDatePayment();
		comment = payment.getComment();
		detail = "cashier=" + payment.getCashier().getFirstLastName();
		amount = payment.getTotal();
		user = new HistoryEntryUser(payment.getUser());
	}

	public HistoryEntry(UsageEO usage) {
		type = HistoryEntryType.USAGE;
		id = usage.getId();
		date = usage.getDateStart();
		comment = usage.getComment();
		detail = usage.getMachine().getName() + " | " + usage.getMinutes() + "min" + " | " + usage.getAdditionalCost() + " CHF additional";
		amount = -((usage.getPricePerHour() * usage.getMinutes()) / 60 + usage.getAdditionalCost());
		user = new HistoryEntryUser(usage.getUser());
	}

	public HistoryEntry(SubscriptionEO subscription) {
		type = HistoryEntryType.SUBSCRIPTION;
		id = subscription.getId();
		date = subscription.getDateSubscription();
		comment = subscription.getComment();
		detail = "Subscription type : " + subscription.getMembershipType().getName()+", duration :"+subscription.getDuration()+" days";
		amount = -subscription.getPrice();
		user = new HistoryEntryUser(subscription.getUser());
	}

	@Override
	public int compareTo(HistoryEntry o) {
		int res = -this.date.compareTo(o.getDate());
		return res == 0 ? Integer.compare(this.id, o.id) : res;
	}

}
