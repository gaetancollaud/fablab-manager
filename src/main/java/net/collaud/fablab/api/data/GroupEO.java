package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_group")
public class GroupEO extends AbstractDataEO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id", nullable = false)
	private Integer groupId;

	@JsonIgnore
	@Column(name = "technicalname", nullable = false)
	private String technicalname;

	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
	private Set<UserEO> users;

	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
	private Set<RoleEO> roles;

	public GroupEO() {
	}

	public GroupEO(Integer groupId) {
		this.groupId = groupId;
	}

	public GroupEO(Integer groupId, String technicalname, String name) {
		this.groupId = groupId;
		this.technicalname = technicalname;
		this.name = name;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getTechnicalname() {
		return technicalname;
	}

	public void setTechnicalname(String technicalname) {
		this.technicalname = technicalname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserEO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEO> users) {
		this.users = users;
	}

	public Set<RoleEO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEO> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (groupId != null ? groupId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GroupEO)) {
			return false;
		}
		GroupEO other = (GroupEO) object;
		if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
