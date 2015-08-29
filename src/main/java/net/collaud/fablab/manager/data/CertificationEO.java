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
 * This is the business class for a <tt>Certification</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_certification")
@Getter
@Setter
@ToString
@Where(clause = "active=1")
public class CertificationEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "certification_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date certificationDate;

    @Column(name = "certification_price", nullable = true)
    private Float certificationPrice;

    @Column(name = "note", nullable = true)
    @Type(type = "text")
    private String note;

    @JoinColumn(name = "training_id", referencedColumnName = "training_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TrainingEO training;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    @JoinTable(name = "r_user_certification",
            joinColumns = {
                @JoinColumn(name = "certification_id", referencedColumnName = "certification_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)//, cascade = CascadeType.ALL)
    private Set<UserEO> users;

    public CertificationEO() {
        this(null);
    }

    public CertificationEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
