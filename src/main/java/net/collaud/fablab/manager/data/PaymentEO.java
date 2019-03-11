package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_payment")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentEO extends AbstractDataEO<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id", nullable = false)
	private Long id;

	@Column(name = "total", nullable = false)
	private double total;

	@Column(name = "date_payement", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePayment;

	@Column(name = "comment")
	private String comment;

	@JsonProperty("payment-user")
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEO user;

	@JsonProperty("payement-cashier")
	@JoinColumn(name = "cashier_id", referencedColumnName = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEO cashier;

	public PaymentEO(Date datePayement, double total, UserEO user, UserEO cashier, String comment) {
		this.id = 0l;
		this.datePayment = datePayement;
		this.total = total;
		this.user = user;
		this.cashier = cashier;
		this.comment = comment;
	}
}
