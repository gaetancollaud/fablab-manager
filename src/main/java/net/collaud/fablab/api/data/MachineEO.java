package net.collaud.fablab.api.data;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "t_machine")
@NamedQueries({
	@NamedQuery(name=MachineEO.FIND_ALL, query="SELECT m "
			+ "FROM MachineEO AS m "
			+ "JOIN FETCH m.machineType"),
	@NamedQuery(name=MachineEO.FIND_BY_ID, query="SELECT m "
			+ "FROM MachineEO AS m "
			+ "JOIN FETCH m.machineType "
			+ "WHERE m.machineId=:"+MachineEO.PARAM_ID),
	@NamedQuery(name=MachineEO.FIND_FOR_USER, query="SELECT m "
			+ "FROM MachineEO AS m "
			+ "JOIN FETCH m.machineType t "
			+ "WHERE t.restricted=false OR t.machineTypeId IN ("
			+ "		SELECT uam.machineType.machineTypeId "
			+ "		FROM UserAuthorizedMachineTypeEO uam "
			+ "		WHERE uam.user=:"+MachineEO.PARAM_USER+""
			+ ")"),
})
public class MachineEO extends AbstractDataEO implements Serializable {
	
	public static final String FIND_ALL = "machine.findAll";
	public static final String FIND_FOR_USER = "machine.findForUser";
	public static final String FIND_BY_ID = "machine.findbyid";
	public static final String PARAM_ID = "machineid";
	public static final String PARAM_USER = "user";
	
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id", nullable = false)
	private Integer machineId;
	
	@Size(max = 45)
    @Column(name = "name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machine", fetch = FetchType.LAZY)
	private List<ReservationEO> reservationList;
	
	@JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineTypeEO machineType;

	public MachineEO() {
	}

	public MachineEO(Integer machineId) {
		this.machineId = machineId;
	}

	@Override
	public Integer getId() {
		return getMachineId();
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public List<ReservationEO> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<ReservationEO> reservationList) {
		this.reservationList = reservationList;
	}

	public MachineTypeEO getMachineType() {
		return machineType;
	}

	public void setMachineType(MachineTypeEO machineType) {
		this.machineType = machineType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (machineId != null ? machineId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MachineEO)) {
			return false;
		}
		MachineEO other = (MachineEO) object;
		if ((this.machineId == null && other.machineId != null) || (this.machineId != null && !this.machineId.equals(other.machineId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.MachineEO[ machineId=" + machineId + " ]";
	}
	
}
