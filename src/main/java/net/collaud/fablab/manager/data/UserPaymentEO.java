package net.collaud.fablab.manager.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.collaud.fablab.manager.data.type.RefundAction;
import static net.collaud.fablab.manager.data.type.RefundAction.PAYMENT;
import net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts;
import org.hibernate.annotations.Type;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_payment")
@Getter
@Setter
@ToString
public class UserPaymentEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Integer id;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "date_payement", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePayment;

    @Column(name = "discount", nullable = true)
    private Double discount;

    @Column(name = "discount_percent", nullable = true, columnDefinition = "TINYINT(1)")
    private boolean discountPercent;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "label", nullable = false)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_credit", nullable = true)
    private HistoryEntryAccounts accountCredit;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_debit", nullable = true)
    private HistoryEntryAccounts accountDebit;

    @Column(name = "note", nullable = true)
    @Type(type = "text")
    private String note;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEO user;

    @JoinColumn(name = "cashier_id", referencedColumnName = "user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UserEO cashier;

    @Column(name = "payed_for_fab_lab", nullable = true, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean payedForFabLab;

    @Enumerated(EnumType.STRING)
    @Column(name = "refund", nullable = true)
    private RefundAction refund;

    @Column(name = "event", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean event;
    
    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public UserPaymentEO() {
        this(null);
    }

    public UserPaymentEO(Integer id) {
        this.active = true;
        this.payedForFabLab = false;
        this.event = false;
        this.refund = PAYMENT;
        this.id = id;
    }

    public UserPaymentEO(Date datePayment, Double amount, UserEO user, UserEO cashier, String note) {
        this.active = true;
        this.payedForFabLab = false;
        this.event = false;
        this.refund = PAYMENT;
        this.datePayment = datePayment;
        this.amount = amount;
        this.note = note;
        this.user = user;
        this.cashier = cashier;
    }

}
