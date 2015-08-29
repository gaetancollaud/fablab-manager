package net.collaud.fablab.manager.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
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
 * This is the business class for a <tt>Group</tt>
 *
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_group")
@Getter
@Setter
@ToString(exclude = "roles")
@Where(clause = "active=1")
public class GroupEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer id;

    @Column(name = "technicalname", nullable = false)
    private String technicalname;

    @Column(name = "name", nullable = false)
    private String name;

    @JoinTable(name = "r_group_role",
            joinColumns = {
                @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RoleEO> roles;

    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public GroupEO() {
        this(null);
    }

    public GroupEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
