package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "r_project_user")
@Getter
@Setter
@ToString
@IdClass(ProjectUserEOPK.class)
public class ProjectUserEO extends AbstractDataEO<ProjectUserEOPK> implements Serializable {

	@Id
	@Column(name = "project_id", nullable = false)
	private int projectId;

	@Id
	@Column(name = "user_id", nullable = false)
	private int userId;

	@Column(name = "can_edit", nullable = false)
	private Boolean canEdit;

	@Column(name = "role")
	private String role;

	@JoinColumn(name = "project_id", referencedColumnName = "project_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ProjectEO project;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEO user;

	@JsonIgnore
	@Override
	public ProjectUserEOPK getId() {
		return new ProjectUserEOPK(projectId, userId);
	}


}
