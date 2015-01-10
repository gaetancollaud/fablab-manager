/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Embeddable
public class PriceMachineEOPK implements Serializable {

	@Column(name = "price_revision_id", nullable = false)
	private int priceRevisionId;

	@Column(name = "machine_type_id", nullable = false)
	private int machineTypeId;

	@Column(name = "membership_type_id", nullable = false)
	private int membershipTypeId;

	public PriceMachineEOPK() {
	}

	public PriceMachineEOPK(int priceRevisionId, int machineTypeId, int membershipTypeId) {
		this.priceRevisionId = priceRevisionId;
		this.machineTypeId = machineTypeId;
		this.membershipTypeId = membershipTypeId;
	}

	public int getPriceRevisionId() {
		return priceRevisionId;
	}

	public void setPriceRevisionId(int priceRevisionId) {
		this.priceRevisionId = priceRevisionId;
	}

	public int getMachineTypeId() {
		return machineTypeId;
	}

	public void setMachineTypeId(int machineTypeId) {
		this.machineTypeId = machineTypeId;
	}

	public int getMembershipTypeId() {
		return membershipTypeId;
	}

	public void setMembershipTypeId(int membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) priceRevisionId;
		hash += (int) machineTypeId;
		hash += (int) membershipTypeId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PriceMachineEOPK)) {
			return false;
		}
		PriceMachineEOPK other = (PriceMachineEOPK) object;
		if (this.priceRevisionId != other.priceRevisionId) {
			return false;
		}
		if (this.machineTypeId != other.machineTypeId) {
			return false;
		}
		if (this.membershipTypeId != other.membershipTypeId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.PricePK[ priceRevisionId=" + priceRevisionId + ", machineTypeId=" + machineTypeId + ", membershipTypeId=" + membershipTypeId + " ]";
	}

}
