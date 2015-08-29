package net.collaud.fablab.manager.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.Type;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_usage")
@Getter
@Setter
@ToString
public class UsageEO extends AbstractDataEO<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usage_id", nullable = false)
    private Integer id;

    @Column(name = "date_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Column(name = "price_hour", nullable = false)
    private double pricePerHour;

    @Column(name = "discount", nullable = true)
    private Double discount;

    @Column(name = "discount_percent", nullable = true, columnDefinition = "TINYINT(1)")
    private boolean discountPercent;

    @Column(name = "minutes")
    private int minutes;

    @Column(name = "additional_cost", nullable = true)
    private double additionalCost;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "note", nullable = true)
    @Type(type = "text")
    private String note;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEO user;

    @JoinColumn(name = "cashier_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEO cashier;

    @JoinColumn(name = "machine_id", referencedColumnName = "machine_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MachineEO machine;

    @JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MembershipTypeEO membershipType;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public UsageEO() {
        this(null);
    }

    public UsageEO(Integer id) {
        this.active = true;
        this.discount = 0.0;
        this.id = id;
    }
}
