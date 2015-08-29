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
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 * This is the business class for a <tt>MachineState</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_machine_state")
@Getter
@Setter
@ToString(exclude = "machineList")
@Where(clause = "active=1")
public class MachineStateEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_state_id", nullable = false)
    private Integer id;
    
    @Column(name = "label", nullable = false)
    private String label;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "machineState", fetch = FetchType.LAZY)
    private List<MachineEO> machineList;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    /**
     * Add a MachineState to this type (bidirectionnal use).
     *
     * @param machine the machine
     */
    public void addMachine(MachineEO machine) {
        this.getMachineList().add(machine);
        machine.setMachineState(this);
    }

    public MachineStateEO() {
        this(null);
    }

    public MachineStateEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
