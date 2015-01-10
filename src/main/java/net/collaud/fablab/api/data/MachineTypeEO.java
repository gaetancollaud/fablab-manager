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
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_machine_type")
@NamedQueries({})
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

	@Column(name = "restricted", nullable = false)
	private boolean restricted;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineType")
	private List<UserAuthorizedMachineTypeEO> usersAuthorizedList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineType", fetch = FetchType.LAZY)
	private List<MachineEO> machineList;

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

	public Integer getMachineTypeId() {
		return machineTypeId;
	}

	public void setMachineTypeId(Integer machineTypeId) {
		this.machineTypeId = machineTypeId;
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

	@XmlTransient
	public List<MachineEO> getMachineList() {
		return machineList;
	}

	public void setMachineEOList(List<MachineEO> machineList) {
		this.machineList = machineList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (machineTypeId != null ? machineTypeId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MachineTypeEO)) {
			return false;
		}
		MachineTypeEO other = (MachineTypeEO) object;
		if ((this.machineTypeId == null && other.machineTypeId != null) || (this.machineTypeId != null && !this.machineTypeId.equals(other.machineTypeId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	@XmlTransient
	public List<PriceMachineEO> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<PriceMachineEO> priceList) {
		this.priceList = priceList;
	}

	public boolean getRestricted() {
		return restricted;
	}

	public void setRestricted(boolean restricted) {
		this.restricted = restricted;
	}

	public List<UserAuthorizedMachineTypeEO> getUsersAuthorizedList() {
		return usersAuthorizedList;
	}

	public void setUsersAuthorizedList(List<UserAuthorizedMachineTypeEO> usersAuthorizedList) {
		this.usersAuthorizedList = usersAuthorizedList;
	}

	@Override
	public int compareTo(MachineTypeEO o) {
		return getMachineTypeId().compareTo(o.getMachineTypeId());
	}

}
