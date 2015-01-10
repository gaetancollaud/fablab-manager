package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Embeddable
public class UserAuthorizedMachineTypeEOPK implements Serializable {

	@Column(name = "user_id", nullable = false)
	private int userId;
	
    @Column(name = "machine_type_id", nullable = false)
	private int machineTypeId;

	public UserAuthorizedMachineTypeEOPK() {
	}

	public UserAuthorizedMachineTypeEOPK(int userId, int machineTypeId) {
		this.userId = userId;
		this.machineTypeId = machineTypeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMachineTypeId() {
		return machineTypeId;
	}

	public void setMachineTypeId(int machineTypeId) {
		this.machineTypeId = machineTypeId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) userId;
		hash += (int) machineTypeId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserAuthorizedMachineTypeEOPK)) {
			return false;
		}
		UserAuthorizedMachineTypeEOPK other = (UserAuthorizedMachineTypeEOPK) object;
		if (this.userId != other.userId) {
			return false;
		}
		if (this.machineTypeId != other.machineTypeId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.UserAuthorizedMachineTypeEOPK[ userId=" + userId + ", machineTypeId=" + machineTypeId + " ]";
	}
	
}
