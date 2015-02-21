package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class MachineEO extends AbstractDataEO<Integer> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_id", nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machine", fetch = FetchType.LAZY)
	private List<ReservationEO> reservationList;

	@JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineTypeEO machineType;

	public MachineEO(Integer id) {
		this.id = id;
	}

}
