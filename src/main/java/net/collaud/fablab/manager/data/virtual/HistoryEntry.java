package net.collaud.fablab.manager.data.virtual;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.MachineTypeEO;
import net.collaud.fablab.manager.data.PaymentEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.type.HistoryEntryType;
import net.collaud.fablab.manager.data.type.PriceUnit;
import net.collaud.fablab.manager.export.CsvExport;
import net.collaud.fablab.manager.export.CsvField;
import net.collaud.fablab.manager.service.util.PriceUtil;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "type"})
@CsvExport(fileName = "accounting")
public class HistoryEntry implements Comparable<HistoryEntry> {

	private final Long id;

	@CsvField(headerName = "type")
	private final HistoryEntryType type;

	@CsvField(headerName = "date")
	private final Date date;

	@CsvField(headerName = "amount")
	private final double amount;

	@CsvField(headerName = "user")
	private final HistoryEntryUser user;

	@CsvField(headerName = "detail")
	private final String detail;

	@CsvField(headerName = "comment")
	private final String comment;

	@JsonIgnore
	@CsvField(headerName = "value")
	private final Double value;

	@JsonIgnore
	@CsvField(headerName = "unit")
	private final PriceUnit unit;

	@JsonIgnore
	@CsvField(headerName = "additionalCost")
	private final Double additionalCost;

	@JsonIgnore
	@CsvField(headerName = "machineType")
	private final String machineType;

	public HistoryEntry(PaymentEO payment) {
		type = payment.getTotal() > 0 ? HistoryEntryType.PAYMENT : HistoryEntryType.REFUND;
		id = payment.getId();
		date = payment.getDatePayment();
		comment = payment.getComment();
		detail = "cashier=" + payment.getCashier().getFirstLastName();
		amount = payment.getTotal();
		user = new HistoryEntryUser(payment.getUser());
		value = null;
		unit = null;
		additionalCost = null;
		machineType = null;
	}

	public HistoryEntry(UsageEO usage) {
		type = HistoryEntryType.USAGE;
		id = usage.getId();
		date = usage.getDateStart();
		comment = usage.getComment();
		detail = usage.getMachine().getName() + " | " + PriceUtil.prettyPrintValue(usage.getAmount(), usage.getUnit()) + " | " + usage.getAdditionalCost() + " CHF additional";
		amount = -(usage.getTotal());
		user = new HistoryEntryUser(usage.getUser());
		value = usage.getAmount();
		unit = usage.getUnit();
		additionalCost = usage.getAdditionalCost();
		machineType = usage.getMachine().getMachineType().getTechnicalname();
	}

	public HistoryEntry(SubscriptionEO subscription) {
		type = HistoryEntryType.SUBSCRIPTION;
		id = subscription.getId();
		date = subscription.getDateSubscription();
		comment = subscription.getComment();
		detail = "Subscription type : " + subscription.getMembershipType().getName() + ", duration :" + subscription.getDuration() + " days";
		amount = -subscription.getPrice();
		user = new HistoryEntryUser(subscription.getUser());
		value = null;
		unit = null;
		additionalCost = null;
		machineType = null;
	}

	@Override
	public int compareTo(HistoryEntry o) {
		int res = -this.date.compareTo(o.getDate());
		return res == 0 ? Long.compare(this.id, o.id) : res;
	}

}
