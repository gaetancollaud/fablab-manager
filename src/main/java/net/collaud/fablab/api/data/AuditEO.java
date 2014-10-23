package net.collaud.fablab.api.data;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import net.collaud.fablab.api.data.type.AuditAction;
import net.collaud.fablab.api.data.type.AuditObject;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "t_audit")
@NamedQueries({
	@NamedQuery(name = AuditEO.SElECT_LAST_ENTRIES, query = "SELECT a FROM AuditEO a ORDER BY a.when DESC"), //	@NamedQuery(name = "LogUserAccess.findAll", query = "SELECT l FROM LogUserAccess l"),
})
public class AuditEO extends AbstractDataEO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SElECT_LAST_ENTRIES = "audit.selectLastEntries";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id", nullable = false)
	private Integer auditId;

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

	public AuditEO() {
	}

	public AuditEO(Integer auditId) {
		this.auditId = auditId;
	}

	public AuditEO(UserEO who, AuditAction action, AuditObject object, Integer objectId, Date when, boolean success, String content, String detail) {
		this.who = who;
		this.action = action;
		this.object = object;
		this.objectId = objectId;
		this.when = when;
		this.success = success;
		this.content = content;
		this.detail = detail;
	}

	@Override
	public Integer getId() {
		return getAuditId();
	}

	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public AuditAction getAction() {
		return action;
	}

	public void setAction(AuditAction action) {
		this.action = action;
	}

	public AuditObject getObject() {
		return object;
	}

	public void setObject(AuditObject object) {
		this.object = object;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public UserEO getWho() {
		return who;
	}

	public void setWho(UserEO who) {
		this.who = who;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (auditId != null ? auditId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AuditEO)) {
			return false;
		}
		AuditEO other = (AuditEO) object;
		if (this.auditId == null || other.auditId == null || !this.auditId.equals(other.auditId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.AuditEO[ auditId=" + auditId + " ]";
	}

}
