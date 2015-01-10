package net.collaud.fablab.api.data.virtual;

import java.util.Date;
import java.util.Objects;
import net.collaud.fablab.api.data.PaymentEO;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageDetailEO;
import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
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
	private final float amount;
	private final String detail;
	private final UserEO user;

	public HistoryEntry(PaymentEO payment) {
		type = HistoryEntryType.PAYMENT;
		id = payment.getPaymentId();
		date = payment.getDatePayment();
		comment = payment.getComment();
		detail = "cashier=" + payment.getCashier().getFirstLastName();
		amount = payment.getTotal();
		user = payment.getUser();
	}

	public HistoryEntry(UsageDetailEO usage) {
		type = HistoryEntryType.USAGE;
		id = usage.getUsageId();
		date = usage.getDateStart();
		comment = usage.getComment();
		detail = usage.getMachine().getName() + " | " + usage.getMinutes() + "min" + " | " + usage.getAdditionalCost() + " CHF additional";
		amount = -((usage.getPrice() * usage.getMinutes()) / 60 + usage.getAdditionalCost());
		user = usage.getUser();
	}

	public HistoryEntry(SubscriptionEO subscription) {
		type = HistoryEntryType.SUBSCRIPTION;
		id = subscription.getSubscriptionId();
		date = subscription.getDateSubscription();
		comment = subscription.getComment();
		detail = "Subscription type : " + subscription.getPriceCotisation().getMembershipType().getName();
		amount = -subscription.getPriceCotisation().getPrice();
		user = subscription.getUser();
	}

	public int getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public Date getDate() {
		return date;
	}

	public float getAmount() {
		return amount;
	}

	public String getDetail() {
		return detail;
	}

	public HistoryEntryType getType() {
		return type;
	}

	public UserEO getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + this.id;
		hash = 29 * hash + Objects.hashCode(this.type);
		hash = 29 * hash + Objects.hashCode(this.date);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final HistoryEntry other = (HistoryEntry) obj;
		if (this.id != other.id) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		if (!Objects.equals(this.date, other.date)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(HistoryEntry o) {
		int res = date.compareTo(o.date);
		if (res != 0) {
			return -res;//inverse
		}
		res = type.compareTo(o.type);
		if (res != 0) {
			return -res;
		}
		return -Integer.valueOf(id).compareTo(Integer.valueOf(o.id));
	}
}
