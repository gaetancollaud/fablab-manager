package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_machine")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MachineEO extends AbstractDataEO<Long> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "introduction", length = 255)
	private String introduction;

	@JsonInclude(JsonInclude.Include.NON_ABSENT)
	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "image_url", length = 255)
	private String image_url;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machine", fetch = FetchType.LAZY)
	private List<ReservationEO> reservationList;

	@JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineTypeEO machineType;

	public MachineEO(Long id) {
		this.id = id;
	}

	@QueryProjection
	public MachineEO(Long id, String name, String introduction, String description, String image_url, MachineTypeEO machineType) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.introduction = introduction;
		this.image_url = image_url;
		this.machineType = machineType;
	}

	@QueryProjection
	public MachineEO(Long id, String name, String introduction, String image_url, MachineTypeEO machineType) {
		this.id = id;
		this.name = name;
		this.introduction = introduction;
		this.image_url = image_url;
		this.machineType = machineType;
	}

}
