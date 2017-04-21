package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.collaud.fablab.manager.data.type.PriceUnit;
import net.collaud.fablab.manager.service.util.recaptcha.PriceUtil;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_usage")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsageEO extends AbstractDataEO<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usage_id", nullable = false)
	private Long id;

	@Column(name = "date_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;

	@Column(name = "equation", nullable = false)
	private String equation;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "unit", nullable = false)
	private PriceUnit unit;

	@Column(name = "additional_cost", nullable = false)
	private double additionalCost;

	@Column(name = "comment")
	private String comment;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEO user;

	@JoinColumn(name = "machine_id", referencedColumnName = "machine_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private MachineEO machine;

	@JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private MembershipTypeEO membershipType;

	@Transient
	@JsonProperty
	private boolean directPaid;

	public double getTotalPrice() {
		return PriceUtil.evaluatePrice(equation, amount)+additionalCost;
	}

	public UsageEO(Date dateStart, String equation, int amount, PriceUnit unit, double additionalCost, String comment, UserEO user, MachineEO machine, MembershipTypeEO membershipType) {
		this.dateStart = dateStart;
		this.equation = equation;
		this.amount = amount;
		this.unit = unit;
		this.additionalCost = additionalCost;
		this.comment = comment;
		this.user = user;
		this.machine = machine;
		this.membershipType = membershipType;
	}


}
