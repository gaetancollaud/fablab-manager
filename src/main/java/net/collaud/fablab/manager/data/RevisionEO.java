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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 * This is the business class for a <tt>Revision</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_revision")
@Getter
@Setter
@ToString
public class RevisionEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revision_id", nullable = false)
    private Integer id;

    @Column(name = "revision_date", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Date revisionDate;
    
    @Column(name = "revision_time", nullable = true, columnDefinition = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date revisionTime;

    @Column(name = "note", nullable = true)
    @Type(type = "text")
    private String note;

    @Column(name = "revision_cost", nullable = true)
    private double cost;

    @JoinColumn(name = "machine_id", referencedColumnName = "machine_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MachineEO machine;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEO user;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public RevisionEO() {
        this(null);
    }

    public RevisionEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
