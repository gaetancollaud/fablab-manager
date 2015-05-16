package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
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
@IdClass(PriceMachineEOPK.class)
public class PriceMachineEO extends AbstractDataEO<PriceMachineEOPK> implements Serializable {

	@Id
	@Column(name = "machine_type_id", nullable = false)
	private int machineTypeId;

	@Id
	@Column(name = "membership_type_id", nullable = false)
	private int membershipTypeId;

	@Column(name = "price", nullable = false)
	private float price;

	@JsonBackReference("machineType-price")
	@JoinColumn(name = "machine_type_id", referencedColumnName = "machine_type_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineTypeEO machineTypeEO;

	@JsonBackReference("membershipType-pice")
	@JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MembershipTypeEO membershipType;

	@JsonIgnore
	@Override
	public PriceMachineEOPK getId() {
		return new PriceMachineEOPK(machineTypeId, membershipTypeId);
	}


}
