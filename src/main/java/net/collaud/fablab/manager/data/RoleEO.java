package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_role")
@Getter
@Setter
@ToString
public class RoleEO extends AbstractDataEO<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "technicalname", nullable = false)
	private String technicalname;

	@JsonIgnore
	@JoinTable(name = "r_group_role",
			joinColumns = {
				@JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, updatable = false)},
			inverseJoinColumns = {
				@JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false, updatable = false)})
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<GroupEO> groups;

	public RoleEO() {
	}

	public RoleEO(Long id) {
		this.id = id;
	}

	public RoleEO(Long roleId, String name) {
		this.id = roleId;
		this.name = name;
	}

}
