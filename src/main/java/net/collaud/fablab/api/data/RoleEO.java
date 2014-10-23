package net.collaud.fablab.api.data;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Gaétan
 */
@Entity
@Table(name = "t_role")
@NamedQueries({
	@NamedQuery(name = "RoleEO.findAll", query = "SELECT r FROM RoleEO r")})
public class RoleEO extends AbstractDataEO<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false)
	private Integer roleId;

	@Size(min = 1, max = 45)
	@Column(name = "name", nullable = false)
	private String name;

	@JoinTable(name = "r_group_role",
			joinColumns = {
				@JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, updatable = false)},
			inverseJoinColumns = {
				@JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false, updatable = false)})
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<GroupEO> groups;

	public RoleEO() {
	}

	public RoleEO(Integer roleId) {
		this.roleId = roleId;
	}

	public RoleEO(Integer roleId, String name) {
		this.roleId = roleId;
		this.name = name;
	}

	@Override
	public Integer getId() {
		return getRoleId();
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<GroupEO> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupEO> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (roleId != null ? roleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof RoleEO)) {
			return false;
		}
		RoleEO other = (RoleEO) object;
		if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.api.data.RoleEO[ roleId=" + roleId + " ]";
	}

}
