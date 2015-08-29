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
import java.util.Set;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>Event</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_event")
@Getter
@Setter
@ToString(exclude={"modules", "organizers", "participants"})
@Where(clause = "active=1")
public class EventEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Integer id;

    @Column(name = "date_start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateStart;

    @Column(name = "date_end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    @Column(name = "time_start", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    @Column(name = "time_end", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "theme", nullable = true)
    private String theme;

    @Column(name = "place", nullable = true)
    private String place;
    
    @Column(name = "price", nullable = true)
    private double price;

    @Column(name = "description", nullable = true)
    @Type(type = "text")
    private String description;

    @JoinColumn(name = "event_type_id", referencedColumnName = "event_type_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private EventTypeEO eventType;

    @JoinColumn(name = "supervisor_id", referencedColumnName = "user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UserEO supervisor;
    
    @JoinTable(name = "r_event_organisator",
            joinColumns = {
                @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = true)},
            inverseJoinColumns = {
                @JoinColumn(name = "event_person_id", referencedColumnName = "event_person_id", nullable = true)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<EventPersonEO> organizers;
    
    @JoinTable(name = "r_event_participant",
            joinColumns = {
                @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = true)},
            inverseJoinColumns = {
                @JoinColumn(name = "event_person_id", referencedColumnName = "event_person_id", nullable = true)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<EventPersonEO> participants;
    
    @JoinTable(name = "r_event_module",
            joinColumns = {
                @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = true)},
            inverseJoinColumns = {
                @JoinColumn(name = "event_module_id", referencedColumnName = "event_module_id", nullable = true)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<EventModuleEO> modules;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public EventEO() {
        this(null);
    }

    public EventEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
