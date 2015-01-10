package net.collaud.fablab.api.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_price_revision")
@NamedQueries({
	@NamedQuery(name = PriceRevisionEO.SELECT_REVISIONS_ORDERED_BY_DATE_DESC,
			query = "SELECT t FROM PriceRevisionEO t ORDER BY t.dateRevision DESC"),})
public class PriceRevisionEO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SELECT_REVISIONS_ORDERED_BY_DATE_DESC = "PriceRevisionEO.selectLast";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_revision_id", nullable = false)
	private Integer priceRevisionId;

	@Column(name = "date_revision", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRevision;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "priceRevision", fetch = FetchType.LAZY)
	private List<PriceCotisationEO> priceCotisationList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "priceRevision", fetch = FetchType.LAZY)
	private List<PriceMachineEO> priceMachineList;

	@Column(name = "membership_duration", nullable = false)
	private int membershipDuration;

	public PriceRevisionEO() {
	}

	public PriceRevisionEO(Integer priceRevisionId) {
		this.priceRevisionId = priceRevisionId;
	}

	public PriceRevisionEO(Integer priceRevisionId, Date dateRevision) {
		this.priceRevisionId = priceRevisionId;
		this.dateRevision = dateRevision;
	}

	public Integer getPriceRevisionId() {
		return priceRevisionId;
	}

	public void setPriceRevisionId(Integer priceRevisionId) {
		this.priceRevisionId = priceRevisionId;
	}

	public Date getDateRevision() {
		return dateRevision;
	}

	public void setDateRevision(Date dateRevision) {
		this.dateRevision = dateRevision;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (priceRevisionId != null ? priceRevisionId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PriceRevisionEO)) {
			return false;
		}
		PriceRevisionEO other = (PriceRevisionEO) object;
		if ((this.priceRevisionId == null && other.priceRevisionId != null) || (this.priceRevisionId != null && !this.priceRevisionId.equals(other.priceRevisionId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.PriceRevisionEO[ priceRevisionId=" + priceRevisionId + " ]";
	}

	public int getMembershipDuration() {
		return membershipDuration;
	}

	public void setMembershipDuration(int membershipDuration) {
		this.membershipDuration = membershipDuration;
	}

	public List<PriceCotisationEO> getPriceCotisationList() {
		return priceCotisationList;
	}

	public void setPriceCotisationList(List<PriceCotisationEO> priceCotisationList) {
		this.priceCotisationList = priceCotisationList;
	}

	public List<PriceMachineEO> getPriceMachineList() {
		return priceMachineList;
	}

	public void setPriceMachineList(List<PriceMachineEO> priceMachineList) {
		this.priceMachineList = priceMachineList;
	}

}
