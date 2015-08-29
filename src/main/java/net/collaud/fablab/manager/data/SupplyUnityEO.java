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
 * This is the business class for a <tt>SupplyUnity</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_supply_unity")
@Getter
@Setter
@ToString(exclude = "supplyList")
@Where(clause = "active=1")
public class SupplyUnityEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supply_unity_id", nullable = false)
    private Integer id;

    @Column(name = "label", nullable = false)
    private String label;
    
    @Column(name = "floating", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean floating;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "supplyUnity", fetch = FetchType.LAZY)
    private List<SupplyEO> supplyList;
    
    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    /**
     * Add a SupplyUnity to this type (bidirectionnal use).
     *
     * @param supply the supply
     */
    public void addSupply(SupplyEO supply) {
        this.getSupplyList().add(supply);
        supply.setSupplyUnity(this);
    }

    public SupplyUnityEO() {
        this(null);
    }

    public SupplyUnityEO(Integer id) {
        this.active = true;
        this.floating = false;
        this.id = id;
    }
}
