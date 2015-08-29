package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Type;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>EventModule</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_event_module")
@Getter
@Setter
@ToString(exclude = {"prerequisites", "dependents", "certified", "modules"})
@Where(clause = "active=1")
public class EventModuleEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_module_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    @Type(type = "text")
    private String description;

    @JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private MachineTypeEO machineType;

    @JoinTable(name = "r_event_module_prerequisite",
            joinColumns = {
                @JoinColumn(name = "event_module_prerequiste", referencedColumnName = "event_module_id", nullable = true)},
            inverseJoinColumns = {
                @JoinColumn(name = "event_module_dependent", referencedColumnName = "event_module_id", nullable = true)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<EventModuleEO> prerequisites;
    
     @JsonIgnore
    @ManyToMany(mappedBy = "modules", fetch = FetchType.LAZY)
    private Set<EventEO> events;

    @JsonIgnore
    @ManyToMany(mappedBy = "prerequisites", fetch = FetchType.LAZY)
    private Set<EventModuleEO> dependents;

    @JsonIgnore
    @ManyToMany(mappedBy = "acquiredModules", fetch = FetchType.LAZY)
    private Set<EventPersonEO> certified;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public EventModuleEO() {
        this(null);
    }

    public EventModuleEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
