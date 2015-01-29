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
public class PriceCotisationEO extends AbstractDataEO<PriceCotisationEOPK> implements Serializable {

	@EmbeddedId
	protected PriceCotisationEOPK id;

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

}
