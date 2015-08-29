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
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>EventPerson</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_event_person")
@Getter
@Setter
@ToString(exclude = {"acquiredModules", "organizers", "participants"})
@Where(clause = "active=1")
public class EventPersonEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_person_id", nullable = true)
    private Integer id;

    @Column(name = "lastname", nullable = true)
    private String lastname;

    @Column(name = "firstname", nullable = true)
    private String firstname;

    @Column(name = "email", nullable = true)
    private String email;

    @JoinTable(name = "r_event_aquired_module",
            joinColumns = {
                @JoinColumn(name = "event_person_id", referencedColumnName = "event_person_id", nullable = true)},
            inverseJoinColumns = {
                @JoinColumn(name = "event_module_id", referencedColumnName = "event_module_id", nullable = true)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<EventModuleEO> acquiredModules;

    @JsonIgnore
    @ManyToMany(mappedBy = "organizers", fetch = FetchType.LAZY)
    private Set<EventEO> organizers;

    @JsonIgnore
    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private Set<EventEO> participants;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public EventPersonEO() {
        this(null);
    }

    public EventPersonEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
