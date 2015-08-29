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
import org.hibernate.annotations.Type;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>Supply</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_supply")
@Getter
@Setter
@ToString
public class SupplyEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supply_id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @Column(name = "unity_buying_price", nullable = true)
    private Double unityBuyingPrice;

    @Column(name = "order_address", nullable = true)
    @Type(type = "text")
    private String orderAddress;

    @Column(name = "quantity_stock", nullable = false)
    private Double quantityStock;

    @JoinColumn(name = "supply_type_id", referencedColumnName = "supply_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SupplyTypeEO supplyType;

    @JoinColumn(name = "supply_unity_id", referencedColumnName = "supply_unity_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SupplyUnityEO supplyUnity;

    @JoinColumn(name = "creation_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEO creationUser;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public SupplyEO() {
        this(null);
    }

    public SupplyEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
