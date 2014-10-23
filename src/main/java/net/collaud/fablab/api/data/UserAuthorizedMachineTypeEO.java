package net.collaud.fablab.api.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "r_user_authorized_machine_type")
@NamedQueries({})
public class UserAuthorizedMachineTypeEO implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected UserAuthorizedMachineTypeEOPK userAuthorizedMachineTypeEOPK;

	@Column(name = "formation_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date formationDate;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private UserEO user;

	@JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private MachineTypeEO machineType;

	public UserAuthorizedMachineTypeEO() {
	}

	public UserAuthorizedMachineTypeEO(UserAuthorizedMachineTypeEOPK userAuthorizedMachineTypeEOPK) {
		this.userAuthorizedMachineTypeEOPK = userAuthorizedMachineTypeEOPK;
	}

	public UserAuthorizedMachineTypeEO(UserAuthorizedMachineTypeEOPK userAuthorizedMachineTypeEOPK, Date formationDate) {
		this.userAuthorizedMachineTypeEOPK = userAuthorizedMachineTypeEOPK;
		this.formationDate = formationDate;
	}

	public UserAuthorizedMachineTypeEO(int userId, int machineTypeId) {
		this.userAuthorizedMachineTypeEOPK = new UserAuthorizedMachineTypeEOPK(userId, machineTypeId);
	}

	public UserAuthorizedMachineTypeEOPK getUserAuthorizedMachineTypeEOPK() {
		return userAuthorizedMachineTypeEOPK;
	}

	public void setUserAuthorizedMachineTypeEOPK(UserAuthorizedMachineTypeEOPK userAuthorizedMachineTypeEOPK) {
		this.userAuthorizedMachineTypeEOPK = userAuthorizedMachineTypeEOPK;
	}

	public Date getFormationDate() {
		return formationDate;
	}

	public void setFormationDate(Date formationDate) {
		this.formationDate = formationDate;
	}

	public UserEO getUser() {
		return user;
	}

	public void setUser(UserEO user) {
		this.user = user;
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
		hash += (userAuthorizedMachineTypeEOPK != null ? userAuthorizedMachineTypeEOPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserAuthorizedMachineTypeEO)) {
			return false;
		}
		UserAuthorizedMachineTypeEO other = (UserAuthorizedMachineTypeEO) object;
		if ((this.userAuthorizedMachineTypeEOPK == null && other.userAuthorizedMachineTypeEOPK != null) || (this.userAuthorizedMachineTypeEOPK != null && !this.userAuthorizedMachineTypeEOPK.equals(other.userAuthorizedMachineTypeEOPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.UserAuthorizedMachineTypeEO[ userAuthorizedMachineTypeEOPK=" + userAuthorizedMachineTypeEOPK + " ]";
	}

}
