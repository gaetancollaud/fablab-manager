package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.collaud.fablab.manager.data.type.PriceUnit;

/**
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
	private Long machineTypeId;

	@Id
	@Column(name = "membership_type_id", nullable = false)
	private Long membershipTypeId;

	@Column(name="equation", nullable = false)
	private String equation;

	@Enumerated(EnumType.STRING)
	@Column(name = "unit", nullable = false)
	private PriceUnit unit;

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
