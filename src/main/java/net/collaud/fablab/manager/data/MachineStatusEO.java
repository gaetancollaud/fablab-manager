package net.collaud.fablab.manager.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_machine_status")
@Getter
@Setter
@ToString(exclude = "machineList")
@Where(clause = "active=1")
public class MachineStatusEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_status_id", nullable = false)
    private Integer id;

    @Column(name = "label", nullable = false)
    private String label;
    
    @Column(name = "color", nullable = false)
    private String color;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "machineStatus", fetch = FetchType.LAZY)
    private List<MachineEO> machineList;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    /**
     * Add a machinery to this type (bidirectionnal use).
     *
     * @param machine the machinery
     */
    public void addMachine(MachineEO machine) {
        this.getMachineList().add(machine);
        machine.setMachineStatus(this);
    }

    public MachineStatusEO() {
        this(null);
    }

    public MachineStatusEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
