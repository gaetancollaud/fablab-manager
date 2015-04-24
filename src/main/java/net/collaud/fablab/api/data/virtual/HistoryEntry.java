package net.collaud.fablab.api.data.virtual;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.api.data.PaymentEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
public class HistoryEntry implements Comparable<HistoryEntry> {

	public enum HistoryEntryType {

		PAYMENT("payment"),
		USAGE("usage"),
		SUBSCRIPTION("subscription");
		private final String css;

		private HistoryEntryType(String css) {
			this.css = css;
		}

		public String getCss() {
			return css;
		}

	}
	private final int id;
	private final HistoryEntryType type;
	private final String comment;
	private final Date date;
	private final double amount;
	private final String detail;
	private final UserEO user;

	public HistoryEntry(PaymentEO payment) {
		type = HistoryEntryType.PAYMENT;
		id = payment.getId();
		date = payment.getDatePayment();
		comment = payment.getComment();
		detail = "cashier=" + payment.getCashier().getFirstLastName();
		amount = payment.getTotal();
		user = payment.getUser();
	}

	public HistoryEntry(UsageEO usage) {
		type = HistoryEntryType.USAGE;
		id = usage.getId();
		date = usage.getDateStart();
		comment = usage.getComment();
		detail = usage.getMachine().getName() + " | " + usage.getMinutes() + "min" + " | " + usage.getAdditionalCost() + " CHF additional";
		amount = -((usage.getPricePerHour() * usage.getMinutes()) / 60 + usage.getAdditionalCost());
		user = usage.getUser();
	}

	public HistoryEntry(SubscriptionEO subscription) {
		type = HistoryEntryType.SUBSCRIPTION;
		id = subscription.getId();
		date = subscription.getDateSubscription();
		comment = subscription.getComment();
		detail = "Subscription type : " + subscription.getPriceCotisation().getMembershipType().getName();
		amount = -subscription.getPriceCotisation().getPrice();
		user = subscription.getUser();
	}

	@Override
	public int compareTo(HistoryEntry o) {
		return -this.date.compareTo(o.getDate());
	}

}
