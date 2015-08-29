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
 * This is the business class for a <tt>TicketStatus</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_ticket_status")
@Getter
@Setter
@ToString(exclude="ticketList")
@Where(clause = "active=1")
public class TicketStatusEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_status_id", nullable = false)
    private Integer id;

    @Column(name = "label", nullable = false)
    private String label;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "status", fetch = FetchType.LAZY)
    private List<TicketEO> ticketList;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    /**
     * Add a ticket to this machine (bidirectionnal use).
     *
     * @param ticket the ticket
     */
    public void addTicket(TicketEO ticket) {
        this.getTicketList().add(ticket);
        ticket.setStatus(this);
    }

    public TicketStatusEO() {
        this(null);
    }

    public TicketStatusEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
