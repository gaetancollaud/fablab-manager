package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_audit")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuditEO extends AbstractDataEO<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id", nullable = false)
	private Long id;

	@Column(name = "action", nullable = false)
	private AuditAction action;

	@Column(name = "object", nullable = false)
	private AuditObject object;

	@Column(name = "object_id", nullable = true)
	private Long objectId;

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

	public AuditEO(Long auditId) {
		this.id = auditId;
	}

	public AuditEO(UserEO who, AuditAction action, AuditObject object, Long objectId, Date when, boolean success, String content, String detail) {
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
	public AuditEO(Long id, AuditAction action, AuditObject object, Long objectId, Date when, boolean success, UserEO who, String content, String detail) {
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
