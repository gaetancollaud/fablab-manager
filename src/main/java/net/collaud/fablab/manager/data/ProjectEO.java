package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_project")
@Data
@NoArgsConstructor
public class ProjectEO extends AbstractDataEO<Integer> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id", nullable = false)
	private Integer id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "date_start", nullable = false)
	private Date dateStart;
	
	@Column(name = "date_end")
	private Date dateEnd;

	@JsonManagedReference("project-users")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.LAZY)
	private Set<ProjectUserEO> projectUsers;


}
