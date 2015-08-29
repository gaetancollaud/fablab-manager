package net.collaud.fablab.manager.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

/**
 * This is the business class for a <tt>Purchase</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_purchase")
@Getter
@Setter
@ToString
public class PurchaseEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", nullable = false)
    private Integer id;

    @Column(name = "purchase_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "discount", nullable = true)
    private Double discount;

    @Column(name = "discount_percent", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean discountPercent;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    @Column(name = "note", nullable = true)
    @Type(type = "text")
    private String note;

    @JoinColumn(name = "supply_id", referencedColumnName = "supply_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SupplyEO supply;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEO user;
    
    @JoinColumn(name = "cashier_id", referencedColumnName = "user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UserEO cashier;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public PurchaseEO() {
        this(null);
    }

    public PurchaseEO(Integer id) {
        this.active = true;
        this.discount = 0.0;
        this.id = id;
    }
}
