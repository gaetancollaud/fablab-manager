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
 * This is the business class for a <tt>MembershipType</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_membership_type")
@Getter
@Setter
@ToString(exclude={"userList", "priceList"})
@Where(clause = "active=1")
public class MembershipTypeEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_type_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "price", nullable = false)
    private double price;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipType", fetch = FetchType.LAZY)
    private List<PriceMachineEO> priceList;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "membershipType", fetch = FetchType.LAZY)
    private List<UserEO> userList;
    
    

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    /**
     * Add a user to this type (bidirectionnal use).
     *
     * @param user the user
     */
    public void addUser(UserEO user) {
        this.getUserList().add(user);
        user.setMembershipType(this);
    }

    public MembershipTypeEO() {
        this(null);
    }

    public MembershipTypeEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
