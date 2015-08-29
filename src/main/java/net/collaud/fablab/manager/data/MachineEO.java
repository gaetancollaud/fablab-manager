package net.collaud.fablab.manager.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

/**
 * This is the business class for a <tt>Machine</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_machine")
@Getter
@Setter
@ToString(exclude = {"revisionList", "reservationList", "ticketList"})
@Where(clause = "active=1")
public class MachineEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "buy_price", nullable = true)
    private double buyPrice;

    @Column(name = "to_do_revision", nullable = true)
    @Type(type = "text")
    private String toDoRevision;

    @Column(name = "acquisition_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date acquisitionDate;

    @JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MachineTypeEO machineType;

    @JoinColumn(name = "machine_status_id", referencedColumnName = "machine_status_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MachineStatusEO machineStatus;

    @JoinColumn(name = "machine_state_id", referencedColumnName = "machine_state_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MachineStateEO machineState;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "machine", fetch = FetchType.LAZY)
    private List<ReservationEO> reservationList;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "machine", fetch = FetchType.LAZY)
    private List<RevisionEO> revisionList;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "machine", fetch = FetchType.LAZY)
    private List<TicketEO> ticketList;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    /**
     * Add a ticket to this machine (bidirectionnal use).
     *
     * @param ticket the ticket
     */
    public void addReservation(TicketEO ticket) {
        this.getTicketList().add(ticket);
        ticket.setMachine(this);
    }

    /**
     * Add a reservation to this machine (bidirectionnal use).
     *
     * @param reservation the reservation
     */
    public void addReservation(ReservationEO reservation) {
        this.getReservationList().add(reservation);
        reservation.setMachine(this);
    }

    /**
     * Add a revision to this machine (bidirectionnal use).
     *
     * @param revision the revision
     */
    public void addRevision(RevisionEO revision) {
        this.getRevisionList().add(revision);
        revision.setMachine(this);
    }

    public MachineEO() {
        this(null);
    }

    public MachineEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
