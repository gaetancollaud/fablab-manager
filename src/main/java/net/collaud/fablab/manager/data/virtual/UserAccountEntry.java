package net.collaud.fablab.manager.data.virtual;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.MotionStockEO;
import net.collaud.fablab.manager.data.PurchaseEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.UserPaymentEO;

import net.collaud.fablab.manager.data.type.HistoryEntryType;
import static net.collaud.fablab.manager.data.type.HistoryEntryType.PAYMENT;
import static net.collaud.fablab.manager.data.type.HistoryEntryType.PURCHASE;
import static net.collaud.fablab.manager.data.type.HistoryEntryType.SUBSCRIPTION;
import static net.collaud.fablab.manager.data.type.HistoryEntryType.USAGE;
import net.collaud.fablab.manager.data.type.RefundAction;
import static net.collaud.fablab.manager.data.type.RefundAction.CREDIT;

/**
 *
 * @author Fabien Vuilleumier
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"ID", "TYPE"})
public class UserAccountEntry implements Comparable<UserAccountEntry> {

    private final int ID;
    private final HistoryEntryType TYPE;
    private final String COMMENT;
    private final Date DATE;
    private final Double AMOUNT;
    private final String DETAIL;
    private final HistoryEntryUser USER;

    public UserAccountEntry(UserPaymentEO payment, UserEO user) {
        boolean cancel = !payment.isActive();
        ID = payment.getId();
        USER = new HistoryEntryUser(user);
        if (payment.getRefund().equals(RefundAction.REFUND)) {
            TYPE = HistoryEntryType.REFUND;
        } else {
            TYPE = PAYMENT;
        }
        DATE = payment.getDatePayment();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(payment.isActive() ? "" : "Canceled");
        detailSb.append(payment.isActive() ? "" : " | ");
        detailSb.append(payment.getLabel());
        DETAIL = detailSb.toString();
        COMMENT = payment.getNote() == null ? "" : payment.getNote();
        Double total = payment.getRefund() == RefundAction.REFUND ? payment.getTotal() : (payment.getRefund() == CREDIT ? payment.getTotal() : -payment.getTotal());
        AMOUNT = !cancel ? total : -total;
    }

    public UserAccountEntry(UsageEO usage, UserEO user) {
        boolean cancel = !usage.isActive();
        ID = usage.getId();
        USER = new HistoryEntryUser(user);
        TYPE = USAGE;
        DATE = usage.getDateStart();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(usage.isActive() ? "" : "Canceled");
        detailSb.append(usage.isActive() ? "" : " | ");
        detailSb.append(usage.getNote() == null ? "" : usage.getNote());
        DETAIL = detailSb.toString();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(usage.getMachine().getName());
        commentSb.append(" | ");
        commentSb.append(usage.getMinutes());
        commentSb.append("min");
        commentSb.append(usage.getAdditionalCost() == 0 ? "" : " | ");
        commentSb.append(usage.getAdditionalCost() == 0 ? "" : usage.getAdditionalCost());
        commentSb.append(usage.getAdditionalCost() == 0 ? "" : " CHF additional ");
        commentSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : " | discount = ");
        commentSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : usage.getDiscount());
        commentSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : " ");
        commentSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : usage.isDiscountPercent() ? "%" : "CHF");
        COMMENT = commentSb.toString();
        AMOUNT = !cancel ? -usage.getTotal() : usage.getTotal();
    }

    public UserAccountEntry(SubscriptionEO subscription, UserEO user) {
        boolean cancel = !subscription.isActive();
        ID = subscription.getId();
        USER = new HistoryEntryUser(user);
        TYPE = SUBSCRIPTION;
        DATE = subscription.getDateSubscription();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(subscription.isActive() ? "" : "Canceled");
        detailSb.append(subscription.isActive() ? "" : " | ");
        detailSb.append(subscription.getComment() == null ? "" : subscription.getComment());
        DETAIL = detailSb.toString();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append("Subscription type : ");
        commentSb.append(subscription.getMembershipType().getName());
        commentSb.append(", duration :");
        commentSb.append(subscription.getDuration());
        commentSb.append(" days");
        COMMENT = commentSb.toString();
        AMOUNT = !cancel ? -subscription.getPrice() : subscription.getPrice();
    }

    public UserAccountEntry(MotionStockEO motionStock, UserEO user) {
        //ALWAYS CORRECTION !
        boolean cancel = !motionStock.isActive();
        ID = motionStock.getId();
        USER = new HistoryEntryUser(user);
        DATE = motionStock.getMotionDate() == null ? new Date() : motionStock.getMotionDate();
        StringBuilder detailSbCor = new StringBuilder();
        detailSbCor.append(motionStock.isActive() ? "" : "Canceled");
        detailSbCor.append(motionStock.isActive() ? "" : " | ");
        detailSbCor.append(motionStock.getIo() == null ? "Erreur" : motionStock.getIo());
        DETAIL = detailSbCor.toString();
        TYPE = HistoryEntryType.PURCHASE;
        StringBuilder commentSbCor = new StringBuilder();
        commentSbCor.append(motionStock.getSupply().getCode());
        commentSbCor.append(" | ");
        commentSbCor.append(motionStock.getQuantity());
        commentSbCor.append(" ");
        commentSbCor.append(motionStock.getSupply().getSupplyUnity().getLabel());
        COMMENT = commentSbCor.toString();
        Double interAmountCor = -(motionStock.getQuantity() * motionStock.getSupply().getSellingPrice());
        AMOUNT = !cancel ? interAmountCor : -interAmountCor;

    }

    public UserAccountEntry(PurchaseEO purchase, UserEO user) {
        boolean cancel = !purchase.isActive();
        ID = purchase.getId();
        USER = new HistoryEntryUser(user);
        TYPE = PURCHASE;
        DATE = purchase.getPurchaseDate();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(purchase.isActive() ? "" : "Canceled");
        detailSb.append(purchase.isActive() ? "" : " | ");
        detailSb.append(purchase.getNote() == null ? "" : purchase.getNote());
        DETAIL = detailSb.toString();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(purchase.getSupply().getCode());
        commentSb.append(" | ");
        commentSb.append(purchase.getQuantity());
        commentSb.append(" ");
        commentSb.append(purchase.getSupply().getSupplyUnity().getLabel());
        commentSb.append(purchase.getDiscount() == null ? purchase.getDiscount() == 0 ? "" : "" : " | discount = ");
        commentSb.append(purchase.getDiscount() == null ? purchase.getDiscount() == 0 ? "" : "" : purchase.getDiscount());
        commentSb.append(purchase.getDiscount() == null ? purchase.getDiscount() == 0 ? "" : "" : " ");
        commentSb.append(purchase.getDiscount() == null ? purchase.getDiscount() == 0 ? "" : "" : purchase.isDiscountPercent() ? "%" : "CHF");
        COMMENT = commentSb.toString();
        Double interAmount = purchase.getQuantity() > 0 ? purchase.getPurchasePrice() : -purchase.getPurchasePrice();
        AMOUNT = !cancel ? -interAmount : interAmount;
    }

    @Override
    public int compareTo(UserAccountEntry o) {
        int res = -this.DATE.compareTo(o.getDATE());
        return res == 0 ? Integer.compare(this.ID, o.getID()) : res;
    }
}
