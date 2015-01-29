package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Embeddable
public class PriceCotisationEOPK implements Serializable {
    @Column(name = "price_revision_id", nullable = false)
	private int priceRevisionId;
	
    @Column(name = "membership_type_id", nullable = false)
	private int membershipTypeId;

	public PriceCotisationEOPK() {
	}

	public PriceCotisationEOPK(int priceRevisionId, int membershipTypeId) {
		this.priceRevisionId = priceRevisionId;
		this.membershipTypeId = membershipTypeId;
	}

	public int getPriceRevisionId() {
		return priceRevisionId;
	}

	public void setPriceRevisionId(int priceRevisionId) {
		this.priceRevisionId = priceRevisionId;
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
		hash += (int) membershipTypeId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PriceCotisationEOPK)) {
			return false;
		}
		PriceCotisationEOPK other = (PriceCotisationEOPK) object;
		if (this.priceRevisionId != other.priceRevisionId) {
			return false;
		}
		if (this.membershipTypeId != other.membershipTypeId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.PriceCotisationPK[ priceRevisionId=" + priceRevisionId + ", membershipTypeId=" + membershipTypeId + " ]";
	}
	
}
