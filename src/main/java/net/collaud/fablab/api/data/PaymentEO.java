package net.collaud.fablab.api.data;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "t_payment")
@NamedQueries({
	@NamedQuery(name = PaymentEO.SELECT_FROM_DATES, query
			= " SELECT p "
			+ " FROM PaymentEO p"
			+ " JOIN FETCH p.user"
			+ " JOIN FETCH p.cashier AS c"
			+ " WHERE p.datePayment<=:" + PaymentEO.PARAM_DATE_BEFORE+" "
					+ " AND p.datePayment >= :"+PaymentEO.PARAM_DATE_AFTER),
	@NamedQuery(name = PaymentEO.SELECT_FROM_USER, query
			= " SELECT p "
			+ " FROM PaymentEO p"
			+ " JOIN FETCH p.cashier AS c"
			+ " WHERE p.user=:" + PaymentEO.PARAM_USER),
	@NamedQuery(name = PaymentEO.SELECT_FROM_IDS,
			query = "SELECT u FROM PaymentEO u WHERE u.paymentId IN :" + PaymentEO.PARAM_IDS),})
public class PaymentEO extends AbstractDataEO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SELECT_FROM_USER = "PaymentEO.selectFromUser";
	public static final String SELECT_FROM_DATES = "PaymentEO.selectFromDates";
	public static final String PARAM_USER = "user";

	public static final String SELECT_FROM_IDS = "PaymentEO.findByIds";
	public static final String PARAM_IDS = "ids";
	public static final String PARAM_DATE_BEFORE = "dateBefore";
	public static final String PARAM_DATE_AFTER = "dateAfter";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id", nullable = false)
	private Integer paymentId;

	@Column(name = "total", nullable = false)
	private float total;

	@Column(name = "date_payement", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datePayment;

	@Size(max = 255)
	@Column(name = "comment")
	private String comment;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEO user;

	@JoinColumn(name = "cashier_id", referencedColumnName = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEO cashier;

	public PaymentEO() {
	}

	public PaymentEO(Date datePayement, float total, UserEO user, UserEO cashier, String comment) {
		this.paymentId = 0;
		this.datePayment = datePayement;
		this.total = total;
		this.user = user;
		this.cashier = cashier;
		this.comment = comment;
	}

	@Override
	public Integer getId() {
		return getPaymentId();
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayement) {
		this.datePayment = datePayement;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserEO getUser() {
		return user;
	}

	public void setUser(UserEO user) {
		this.user = user;
	}

	public UserEO getCashier() {
		return cashier;
	}

	public void setCashier(UserEO cashier) {
		this.cashier = cashier;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (paymentId != null ? paymentId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PaymentEO)) {
			return false;
		}
		PaymentEO other = (PaymentEO) object;
		if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.PaymentEO[ paymentId=" + paymentId + " ]";
	}

}
