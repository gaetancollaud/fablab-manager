package net.collaud.fablab.api.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "r_price_cotisation")
@NamedQueries({
	@NamedQuery(name = PriceCotisationEO.SELECT_FROM_MEMBERSHIP_TYPE_AND_REVISION,
			query = "SELECT pc FROM PriceCotisationEO pc "
					+ "WHERE pc.membershipType=:" + PriceCotisationEO.PARAM_MEMBERSHIP_TYPE+" "
					+ "AND pc.priceRevision=:"+PriceCotisationEO.PARAM_REVISION),})
public class PriceCotisationEO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SELECT_FROM_MEMBERSHIP_TYPE_AND_REVISION = "PriceCotisationEO.selectFromMembershipAndRevision";
	public static final String PARAM_MEMBERSHIP_TYPE = "membershiptype";
	public static final String PARAM_REVISION = "revision";

	@EmbeddedId
	protected PriceCotisationPK priceCotisationPK;

	@Column(name = "price", nullable = false)
	private float price;

	@JoinColumn(name = "price_revision_id", referencedColumnName = "price_revision_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PriceRevisionEO priceRevision;

	@JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MembershipTypeEO membershipType;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "priceCotisation")
	private List<SubscriptionEO> subscriptionList;

	public PriceCotisationEO() {
	}

	public PriceCotisationEO(PriceCotisationPK priceCotisationPK) {
		this.priceCotisationPK = priceCotisationPK;
	}

	public PriceCotisationEO(PriceCotisationPK priceCotisationPK, float price) {
		this.priceCotisationPK = priceCotisationPK;
		this.price = price;
	}

	public PriceCotisationEO(int priceRevisionId, int membershipTypeId) {
		this.priceCotisationPK = new PriceCotisationPK(priceRevisionId, membershipTypeId);
	}

	public PriceCotisationPK getPriceCotisationPK() {
		return priceCotisationPK;
	}

	public void setPriceCotisationPK(PriceCotisationPK priceCotisationPK) {
		this.priceCotisationPK = priceCotisationPK;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public PriceRevisionEO getPriceRevision() {
		return priceRevision;
	}

	public void setPriceRevision(PriceRevisionEO priceRevision) {
		this.priceRevision = priceRevision;
	}

	public MembershipTypeEO getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipTypeEO membershipType) {
		this.membershipType = membershipType;
	}

	public List<SubscriptionEO> getSubscriptionList() {
		return subscriptionList;
	}

	public void setSubscriptionList(List<SubscriptionEO> subscriptionList) {
		this.subscriptionList = subscriptionList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (priceCotisationPK != null ? priceCotisationPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PriceCotisationEO)) {
			return false;
		}
		PriceCotisationEO other = (PriceCotisationEO) object;
		if ((this.priceCotisationPK == null && other.priceCotisationPK != null) || (this.priceCotisationPK != null && !this.priceCotisationPK.equals(other.priceCotisationPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.PriceCotisation[ priceCotisationPK=" + priceCotisationPK + " ]";
	}

}
