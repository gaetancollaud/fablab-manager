package net.collaud.fablab.manager.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Where;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the business class for a <tt>EventType</tt>
 * @author Fabien Vuilleumier
 */
@Entity
@Table(name = "t_event_type")
@Getter
@Setter
@ToString
@Where(clause="active=1")
public class EventTypeEO extends AbstractDataEO<Integer> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_type_id", nullable = false)
    private Integer id;

    @Column(name = "label", nullable = false )
    private String label;

    @Column(name="active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active;

    public EventTypeEO() {
        this(null);
    }

    public EventTypeEO(Integer id) {
        this.active = true;
        this.id = id;
    }
}
