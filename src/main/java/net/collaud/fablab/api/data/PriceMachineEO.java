package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "r_price_machine")
@NamedQueries({})
public class PriceMachineEO extends AbstractDataEO implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PriceMachineEOPK pricePK;

	@Column(name = "price", nullable = false)
	private float price;

	@JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineTypeEO machineTypeEO;

	@JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MembershipTypeEO membershipType;

	@JoinColumn(name = "price_revision_id", referencedColumnName = "price_revision_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PriceRevisionEO priceRevision;

	public PriceMachineEO() {
	}

	public PriceMachineEO(PriceMachineEOPK pricePK) {
		this.pricePK = pricePK;
	}

	public PriceMachineEO(PriceMachineEOPK pricePK, float price) {
		this.pricePK = pricePK;
		this.price = price;
	}

	public PriceMachineEO(int year, int machineTypeId, int membershipTypeId) {
		this.pricePK = new PriceMachineEOPK(year, machineTypeId, membershipTypeId);
	}

	@Override
	public Integer getId() {
		throw new RuntimeException("Not implemented yet");
	}

	public PriceMachineEOPK getPricePK() {
		return pricePK;
	}

	public void setPricePK(PriceMachineEOPK pricePK) {
		this.pricePK = pricePK;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public MachineTypeEO getMachineTypeEO() {
		return machineTypeEO;
	}

	public void setMachineTypeEO(MachineTypeEO machineTypeEO) {
		this.machineTypeEO = machineTypeEO;
	}

	public MembershipTypeEO getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipTypeEO membershipType) {
		this.membershipType = membershipType;
	}

	public PriceRevisionEO getPriceRevision() {
		return priceRevision;
	}

	public void setPriceRevision(PriceRevisionEO priceRevision) {
		this.priceRevision = priceRevision;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pricePK != null ? pricePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PriceMachineEO)) {
			return false;
		}
		PriceMachineEO other = (PriceMachineEO) object;
		if ((this.pricePK == null && other.pricePK != null) || (this.pricePK != null && !this.pricePK.equals(other.pricePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.Price[ pricePK=" + pricePK + " ]";
	}

}
