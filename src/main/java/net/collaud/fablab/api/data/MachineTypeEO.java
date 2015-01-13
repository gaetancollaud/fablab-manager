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
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_machine_type")
@NamedQueries({})
@Getter
@Setter
public class MachineTypeEO extends AbstractDataEO implements Serializable, Comparable<MachineTypeEO> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_type_id", nullable = false)
	private Integer machineTypeId;

	@Column(name = "technicalname", nullable = false)
	private String technicalname;

	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@Column(name = "restricted", nullable = false)
	private boolean restricted;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineType")
	private List<UserAuthorizedMachineTypeEO> usersAuthorizedList;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineType", fetch = FetchType.LAZY)
	private List<MachineEO> machineList;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineTypeEO", fetch = FetchType.LAZY)
	private List<PriceMachineEO> priceList;

	public MachineTypeEO() {
	}

	public MachineTypeEO(Integer machineTypeId) {
		this.machineTypeId = machineTypeId;
	}

	public MachineTypeEO(Integer machineTypeId, String name) {
		this.machineTypeId = machineTypeId;
		this.name = name;
	}

	@Override
	public int compareTo(MachineTypeEO o) {
		return getMachineTypeId().compareTo(o.getMachineTypeId());
	}

}
