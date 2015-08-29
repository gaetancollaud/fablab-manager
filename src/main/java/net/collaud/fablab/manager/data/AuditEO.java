package net.collaud.fablab.manager.data;

import com.mysema.query.annotations.QueryProjection;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import org.hibernate.annotations.Where;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_audit")
@Getter
@Setter
@ToString
@Where(clause = "active=1")
@NoArgsConstructor
//@AllArgsConstructor(onConstructor = @_({
//	@QueryProjection
//}))
public class AuditEO extends AbstractDataEO<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id", nullable = false)
	private Integer id;

	@Column(name = "action", nullable = false)
	private AuditAction action;

	@Column(name = "object", nullable = false)
	private AuditObject object;

	@Column(name = "object_id", nullable = true)
	private Integer objectId;

	@Column(name = "dateandtime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date when;

	@Column(name = "success", nullable = false)
	private boolean success;

	@JoinColumn(name = "who", referencedColumnName = "user_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private UserEO who;

	@Column(name = "content", nullable = true)
	private String content;

	@Column(name = "detail", nullable = true)
	private String detail;

	@Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	private boolean active;

	public AuditEO(UserEO who, AuditAction action, AuditObject object, Integer objectId, Date when, boolean success, String content, String detail) {
		this.active = true;
		this.who = who;
		this.action = action;
		this.object = object;
		this.objectId = objectId;
		this.when = when;
		this.success = success;
		this.content = content;
		this.detail = detail;
	}

	@QueryProjection
	public AuditEO(Integer id, AuditAction action, AuditObject object, Integer objectId, Date when, boolean success, UserEO who, String content, String detail) {
		this.id = id;
		this.action = action;
		this.object = object;
		this.objectId = objectId;
		this.when = when;
		this.success = success;
		this.who = who;
		this.content = content;
		this.detail = detail;
	}

}
