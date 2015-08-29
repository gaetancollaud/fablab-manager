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

/**
 * This is the business class for a <tt>MotionStock</tt>
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_motion_stock")
@Getter
@Setter
@ToString
@Where(clause="active=1")
public class MotionStockEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motion_stock_id", nullable = false)
    private Integer id;

    @Column(name = "motion_date", nullable = false )
    @Temporal(TemporalType.DATE)
    private Date motionDate;

    @Column(name = "quantity", nullable = false )
    private Double quantity;

    @Column(name = "io", nullable = false )
    private String io;

    @JoinColumn(name = "supply_id", referencedColumnName = "supply_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SupplyEO supply;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UserEO user;

    @Column(name="active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public MotionStockEO() {
        this(null);
    }

    public MotionStockEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
