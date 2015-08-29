package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import org.hibernate.annotations.Type;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 * This is the business class for a <tt>Ticket</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_ticket")
@Getter
@Setter
@ToString
@Where(clause = "active=1")
public class TicketEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    @Type(type = "text")
    private String description;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "prevision_close_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date previsionCloseDate;

    @Column(name = "close_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date closeDate;

    @JoinColumn(name = "machine_id", referencedColumnName = "machine_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MachineEO machine;

    @JoinColumn(name = "ticket_status_id", referencedColumnName = "ticket_status_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TicketStatusEO status;

    @JoinColumn(name = "creation_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEO creationUser;

    @JoinColumn(name = "close_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private UserEO closeUser;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public TicketEO() {
        this(null);
    }

    public TicketEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
