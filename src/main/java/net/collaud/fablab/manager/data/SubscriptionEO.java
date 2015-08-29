package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_subscription")
@Getter
@Setter
@ToString
public class SubscriptionEO extends AbstractDataEO<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    private Integer id;

    @Column(name = "date_subscription", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubscription;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "comment")
    private String comment;

    @JsonBackReference("user-subscription")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEO user;

    @JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MembershipTypeEO membershipType;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public SubscriptionEO() {
        this(null);
    }

    public SubscriptionEO(Integer id) {
        this.active = true;
        this.id = id;
    }

}
