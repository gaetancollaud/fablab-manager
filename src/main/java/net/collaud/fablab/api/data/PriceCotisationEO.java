package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "r_price_cotisation")
@Getter
@Setter
@ToString
@IdClass(PriceCotisationEOPK.class)
public class PriceCotisationEO extends AbstractDataEO<PriceCotisationEOPK> implements Serializable {

	@Id
    @Column(name = "price_revision_id", nullable = false)
	private int priceRevisionId;
	
	@Id
    @Column(name = "membership_type_id", nullable = false)
	private int membershipTypeId;

	@Column(name = "price", nullable = false)
	private float price;

	@JsonBackReference
	@JoinColumn(name = "price_revision_id", referencedColumnName = "price_revision_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PriceRevisionEO priceRevision;

	@JsonIgnore
	@JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MembershipTypeEO membershipType;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "priceCotisation")
	private List<SubscriptionEO> subscriptionList;

	@JsonIgnore
	@Override
	public PriceCotisationEOPK getId() {
		return new PriceCotisationEOPK(priceRevisionId, membershipTypeId);
	}

}
