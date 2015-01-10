package net.collaud.fablab.api.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_system_status")
@NamedQueries({
	@NamedQuery(name = SystemStatusEO.FIND_BY_NAME,
			query = "SELECT s FROM SystemStatusEO s WHERE s.name=:" + SystemStatusEO.PARAM_NAME)
})
public class SystemStatusEO extends AbstractDataEO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_NAME = "SystemStatusEO.findByName";
	public static final String PARAM_NAME = "name";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "system_status_id", nullable = false)
	private Integer systemStatusId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private String type;

	@Lob
	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "last_check", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastCheck;

	@Column(name = "notify", nullable = false)
	private Boolean notify;

	public SystemStatusEO() {
	}

	public SystemStatusEO(Integer systemStatusId) {
		this.systemStatusId = systemStatusId;
	}

	public SystemStatusEO(Integer systemStatusId, String name, String type, String content, Date lastCheck, boolean notify) {
		this.systemStatusId = systemStatusId;
		this.name = name;
		this.type = type;
		this.content = content;
		this.lastCheck = lastCheck;
		this.notify = notify;
	}

	public Integer getSystemStatusId() {
		return systemStatusId;
	}

	public void setSystemStatusId(Integer systemStatusId) {
		this.systemStatusId = systemStatusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(Date lastCheck) {
		this.lastCheck = lastCheck;
	}

	public Boolean isNotify() {
		return notify;
	}

	public void setNotify(Boolean notify) {
		this.notify = notify;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (systemStatusId != null ? systemStatusId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SystemStatusEO)) {
			return false;
		}
		SystemStatusEO other = (SystemStatusEO) object;
		if ((this.systemStatusId == null && other.systemStatusId != null) || (this.systemStatusId != null && !this.systemStatusId.equals(other.systemStatusId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SystemStatusEO{" + "systemStatusId=" + systemStatusId + ", name=" + name + ", type=" + type + ", content=" + content + ", lastCheck=" + lastCheck + '}';
	}

}
