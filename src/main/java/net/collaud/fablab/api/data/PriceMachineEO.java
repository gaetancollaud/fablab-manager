package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "r_price_machine")
@Getter
@Setter
@ToString
public class PriceMachineEO extends AbstractDataEO<PriceMachineEOPK> implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PriceMachineEOPK id;

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

}
