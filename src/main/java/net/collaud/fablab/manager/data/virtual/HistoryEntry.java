package net.collaud.fablab.manager.data.virtual;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.MotionStockEO;
import net.collaud.fablab.manager.data.PurchaseEO;
import net.collaud.fablab.manager.data.RevisionEO;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UsageEO;
import net.collaud.fablab.manager.data.UserPaymentEO;
import net.collaud.fablab.manager.data.type.HistoryEntryType;
import net.collaud.fablab.manager.data.type.RefundAction;
import static net.collaud.fablab.manager.data.type.RefundAction.CREDIT;
import static net.collaud.fablab.manager.data.type.RefundAction.REFUND;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.CAISSE_POSTE_BANQUE;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.CREANCES_CLIENT;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.DETTES_FOURNISSEUR;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.ENTRETIEN_MACHINE;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.HONORAIRES;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.PRODUIT_EXCEPTIONNEL;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.COTISATIONS;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.STOCK_DIVERS;
import static net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts.VENTES_MARCHANDISES;
import net.collaud.fablab.manager.export.CsvField;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "type"})
public class HistoryEntry implements Comparable<HistoryEntry> {

	private final int id;
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
	
	private HistoryEntryAccounts ACCOUNT_CREDIT;
	private HistoryEntryAccounts ACCOUNT_DEBIT;

    public HistoryEntry(UserPaymentEO payment) {
        boolean cancel = !payment.isActive();
        boolean forTheLab = payment.isPayedForFabLab();
        boolean cash = payment.getCashier() != null;
        boolean refund = payment.getRefund() == REFUND || payment.getRefund() == CREDIT;
        boolean event = payment.isEvent();
        if (event) {
            type = HistoryEntryType.EVENT;
        } else if (payment.getRefund().equals(RefundAction.REFUND)) {
            type = HistoryEntryType.REFUND;
        } else {
            type = HistoryEntryType.PAYMENT;
        }
        id = payment.getId();
        date = payment.getDatePayment();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(payment.isActive() ? "" : "Canceled | ");
        commentSb.append(payment.getNote() == null ? "" : " | ");
        commentSb.append(payment.getNote() == null ? "" : payment.getNote());
        comment = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(payment.getLabel());
        detailSb.append(payment.isPayedForFabLab() ? "" : cash ? " | cashier=" + payment.getCashier().getFirstLastName() : " | Use it's credit");
        detailSb.append(payment.isPayedForFabLab() ? " | Payed for the lab" : " | Payed for the user ");
        detail = detailSb.toString();
        double interAmount = forTheLab ? -payment.getTotal() : payment.getTotal();
        double inter2Amount = event ? -interAmount : interAmount;
        amount = !cancel ? inter2Amount : -inter2Amount;
        user = new HistoryEntryUser(payment.getUser());
        if (!cancel) {
            if (forTheLab) {
                ACCOUNT_CREDIT = payment.getAccountCredit();
                ACCOUNT_DEBIT = payment.getAccountDebit();
            } else {
                if (refund) {
                    if (cash) {
                        ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                        ACCOUNT_DEBIT = CREANCES_CLIENT;
                    } else {
                        //don't possible
                        ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                        ACCOUNT_DEBIT = CREANCES_CLIENT;
                    }
                } else {
                    if (cash) {
                        ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                        ACCOUNT_DEBIT = PRODUIT_EXCEPTIONNEL;
                    } else {
                        ACCOUNT_CREDIT = CREANCES_CLIENT;
                        ACCOUNT_DEBIT = PRODUIT_EXCEPTIONNEL;
                    }
                }
            }
        } else {
            if (forTheLab) {
                ACCOUNT_CREDIT = payment.getAccountDebit();
                ACCOUNT_DEBIT = payment.getAccountCredit();
            } else {
                if (!refund) {
                    if (cash) {
                        ACCOUNT_CREDIT = PRODUIT_EXCEPTIONNEL;
                        ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
                    } else {
                        ACCOUNT_CREDIT = PRODUIT_EXCEPTIONNEL;
                        ACCOUNT_DEBIT = CREANCES_CLIENT;
                    }
                } else {
                    if (cash) {
                        ACCOUNT_CREDIT = CREANCES_CLIENT;
                        ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
                    } else {
                        //don't possible
                        ACCOUNT_CREDIT = CREANCES_CLIENT;
                        ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
                    }
                }
            }
        }
    }

    public HistoryEntry(UsageEO usage) {
        boolean cancel = !usage.isActive();
        boolean cash = usage.getCashier() != null;
        type = HistoryEntryType.USAGE;
        id = usage.getId();
        date = usage.getDateStart();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(usage.isActive() ? "" : "Canceled");
        commentSb.append(usage.isActive() ? "" : " | ");
        commentSb.append(usage.getNote() == null ? "" : usage.getNote());
        comment = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(usage.getMachine().getName());
        detailSb.append(" | ");
        detailSb.append(usage.getMinutes());
        detailSb.append("min");
        detailSb.append(" | ");
        detailSb.append(usage.getAdditionalCost() == 0 ? "" : usage.getAdditionalCost());
        detailSb.append(usage.getAdditionalCost() == 0 ? "" : " CHF additional | ");
        detailSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : " discount = ");
        detailSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : usage.getDiscount());
        detailSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : " ");
        detailSb.append(usage.getDiscount() == null ? usage.getDiscount() == 0 ? "" : "" : usage.isDiscountPercent() ? "% | " : "CHF | ");
        detailSb.append(cash ? "cashier=" + usage.getCashier().getFirstLastName() : "Use it's credit");
        detail = detailSb.toString();
        double interAmount = usage.getTotal();
        amount = !cancel ? interAmount : -interAmount;
        user = new HistoryEntryUser(usage.getUser());
        if (!cancel) {
            if (cash) {
                ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                ACCOUNT_DEBIT = HONORAIRES;
            } else {
                ACCOUNT_CREDIT = CREANCES_CLIENT;
                ACCOUNT_DEBIT = HONORAIRES;
            }
        } else {
            if (cash) {
                ACCOUNT_CREDIT = HONORAIRES;
                ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
            } else {
                ACCOUNT_CREDIT = HONORAIRES;
                ACCOUNT_DEBIT = CREANCES_CLIENT;
            }
        }

    }

    public HistoryEntry(RevisionEO revision) {
        boolean cancel = !revision.isActive();
        type = HistoryEntryType.REVISION;
        id = revision.getId();
        date = revision.getRevisionDate();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(revision.isActive() ? "" : "Canceled");
        commentSb.append(revision.isActive() ? "" : " | ");
        commentSb.append(revision.getNote() == null ? "" : revision.getNote());
        comment = commentSb.toString();
        detail = revision.getMachine().getName();
        double interAmount = -revision.getCost();
        amount = !cancel ? interAmount : -interAmount;
        user = new HistoryEntryUser(revision.getUser());
        if (!cancel) {
            ACCOUNT_CREDIT = ENTRETIEN_MACHINE;
            ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
        } else {
            ACCOUNT_CREDIT = DETTES_FOURNISSEUR;
            ACCOUNT_DEBIT = ENTRETIEN_MACHINE;
        }

    }

    public HistoryEntry(SubscriptionEO subscription) {
        boolean cancel = !subscription.isActive();
        type = HistoryEntryType.SUBSCRIPTION;
        id = subscription.getId();
        date = subscription.getDateSubscription();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(subscription.isActive() ? "" : "Canceled");
        commentSb.append(subscription.isActive() ? "" : " | ");
        commentSb.append(subscription.getComment() == null ? "" : subscription.getComment());
        comment = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append("Subscription type : ");
        detailSb.append(subscription.getMembershipType().getName());
        detailSb.append(", duration :");
        detailSb.append(subscription.getDuration());
        detailSb.append(" days");
        detail = detailSb.toString();
        double interAmount = -subscription.getPrice();
        amount = !cancel ? interAmount : -interAmount;
        user = new HistoryEntryUser(subscription.getUser());
        if (!cancel) {
            ACCOUNT_CREDIT = CREANCES_CLIENT;
            ACCOUNT_DEBIT = COTISATIONS;
        } else {
            ACCOUNT_CREDIT = COTISATIONS;
            ACCOUNT_DEBIT = CREANCES_CLIENT;
        }

    }

    public HistoryEntry(MotionStockEO motionStock) {
        boolean cancel = !motionStock.isActive();
        id = motionStock.getId() == null ? 0 : motionStock.getId();
        date = motionStock.getMotionDate() == null ? new Date() : motionStock.getMotionDate();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(motionStock.isActive() ? "" : "Canceled");
        commentSb.append(motionStock.isActive() ? "" : " | ");
        commentSb.append(motionStock.getIo() == null ? "Erreur" : motionStock.getIo());
        comment = commentSb.toString();
        switch (motionStock.getIo()) {
            case "Entr�e":
            case "Entr�e [ajout]":
                type = HistoryEntryType.SUPPLY;
                StringBuilder detailSb = new StringBuilder();
                detailSb.append(motionStock.getSupply().getCode());
                detailSb.append(" | ");
                detailSb.append(motionStock.getQuantity());
                detailSb.append(" ");
                detailSb.append(motionStock.getSupply().getSupplyUnity().getLabel());
                detail = detailSb.toString();
                double interAmount = -(motionStock.getQuantity() * motionStock.getSupply().getUnityBuyingPrice());
                amount = !cancel ? interAmount : -interAmount;
                user = new HistoryEntryUser(motionStock.getUser());
                if (!cancel) {
                    ACCOUNT_CREDIT = STOCK_DIVERS;
                    ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
                } else {
                    ACCOUNT_CREDIT = STOCK_DIVERS;
                    ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
                }

                break;
            case "Correction":
                type = HistoryEntryType.PURCHASE;
                StringBuilder detailSbCor = new StringBuilder();
                detailSbCor.append(motionStock.getSupply().getCode());
                detailSbCor.append(" | ");
                detailSbCor.append(motionStock.getQuantity());
                detailSbCor.append(" ");
                detailSbCor.append(motionStock.getSupply().getSupplyUnity().getLabel());
                detail = detailSbCor.toString();
                double interAmountCor = -(motionStock.getQuantity() * motionStock.getSupply().getSellingPrice());
                amount = motionStock.isActive() ? interAmountCor : -interAmountCor;
                user = new HistoryEntryUser(motionStock.getUser());
                ACCOUNT_CREDIT = DETTES_FOURNISSEUR;
                ACCOUNT_DEBIT = STOCK_DIVERS;
                break;
            default:
                type = HistoryEntryType.SUPPLY;
                detail = "Erreur";
                amount = 0;
                user = new HistoryEntryUser(null);
                ACCOUNT_CREDIT = STOCK_DIVERS;
                ACCOUNT_DEBIT = DETTES_FOURNISSEUR;
                break;
        }
    }

    public HistoryEntry(PurchaseEO purchase) {
        boolean cancel = !purchase.isActive();
        boolean cash = purchase.getCashier() != null;
        type = HistoryEntryType.PURCHASE;
        id = purchase.getId();
        date = purchase.getPurchaseDate();
        StringBuilder commentSb = new StringBuilder();
        commentSb.append(purchase.isActive() ? "" : "Canceled");
        commentSb.append(purchase.isActive() ? "" : " | ");
        commentSb.append(purchase.getNote() == null ? "" : purchase.getNote());
        comment = commentSb.toString();
        StringBuilder detailSb = new StringBuilder();
        detailSb.append(purchase.getSupply().getCode());
        detailSb.append(" | ");
        detailSb.append(purchase.getQuantity());
        detailSb.append(" | ");
        detailSb.append(purchase.getSupply().getSupplyUnity().getLabel());
        detailSb.append(purchase.getDiscount() == null ? "" : purchase.getDiscount() == 0 ? "" : " discount = ");
        detailSb.append(purchase.getDiscount() == null ? "" : purchase.getDiscount() == 0 ? "" : purchase.getDiscount());
        detailSb.append(" ");
        detailSb.append(purchase.getDiscount() == null ? "" : purchase.getDiscount() == 0 ? "" : purchase.isDiscountPercent() ? "%" : "CHF");
        detailSb.append(purchase.getDiscount() == null ? "" : purchase.getDiscount() == 0 ? "" : " | ");
        detailSb.append(cash ? "cashier=" + purchase.getCashier().getFirstLastName() : "Use it's credit");
        detail = detailSb.toString();
        double interAmount = purchase.getQuantity() > 0 ? purchase.getPurchasePrice() : -purchase.getPurchasePrice();
        amount = !cancel ? interAmount : -interAmount;
        user = new HistoryEntryUser(purchase.getUser());
        if (!cancel) {
            if (cash) {
                ACCOUNT_CREDIT = CAISSE_POSTE_BANQUE;
                ACCOUNT_DEBIT = VENTES_MARCHANDISES;
            } else {
                ACCOUNT_CREDIT = CREANCES_CLIENT;
                ACCOUNT_DEBIT = VENTES_MARCHANDISES;
            }
        } else {
            if (cash) {
                ACCOUNT_CREDIT = VENTES_MARCHANDISES;
                ACCOUNT_DEBIT = CAISSE_POSTE_BANQUE;
            } else {
                ACCOUNT_CREDIT = VENTES_MARCHANDISES;
                ACCOUNT_DEBIT = CREANCES_CLIENT;
            }
        }
    }

    @Override
    public int compareTo(HistoryEntry o) {
        int res = -this.date.compareTo(o.getDate());
        return res == 0 ? Integer.compare(this.id, o.getId()) : res;
    }

}
