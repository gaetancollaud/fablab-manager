package net.collaud.fablab.manager.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.List;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>SupplyType</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_supply_type")
@Getter
@Setter
@ToString(exclude = "supplyList")
@Where(clause = "active=1")
public class SupplyTypeEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supply_type_id", nullable = false)
    private Integer id;

    @Column(name = "label", nullable = false)
    private String label;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "supplyType", fetch = FetchType.LAZY)
    private List<SupplyEO> supplyList;

    /**
     * Add a SupplyType to this type (bidirectionnal use).
     *
     * @param supply the supply
     */
    public void addSupply(SupplyEO supply) {
        this.getSupplyList().add(supply);
        supply.setSupplyType(this);
    }
    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public SupplyTypeEO() {
        this(null);
    }

    public SupplyTypeEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
