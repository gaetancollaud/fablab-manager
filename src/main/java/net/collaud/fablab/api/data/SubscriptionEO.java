package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_subscription")
@NamedQueries({
	@NamedQuery(name = SubscriptionEO.SELECT_FROM_DATES,
			query = "SELECT s FROM SubscriptionEO s"
			+ " JOIN FETCH s.user"
			+ " WHERE s.dateSubscription<=:" + SubscriptionEO.PARAM_DATE_BEFORE + " "
			+ " AND s.dateSubscription>=:" + SubscriptionEO.PARAM_DATE_AFTER),
	@NamedQuery(name = SubscriptionEO.SELECT_FROM_USER,
			query = "SELECT s FROM SubscriptionEO s WHERE s.user=:" + SubscriptionEO.PARAM_USER),})
public class SubscriptionEO extends AbstractDataEO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SELECT_FROM_USER = "SubscriptionEO.selectFromUser";
	public static final String SELECT_FROM_DATES = "SubscriptionEO.selectFromDates";

	public static final String PARAM_USER = "user";
	public static final String PARAM_DATE_BEFORE = "dateBefore";
	public static final String PARAM_DATE_AFTER = "dateAfter";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_id", nullable = false)
	private Integer subscriptionId;

	@Column(name = "comment")
	private String comment;

	@Column(name = "date_subscription", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSubscription;

	@JsonBackReference
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEO user;

	@JoinColumns({
		@JoinColumn(name = "price_revision_id", referencedColumnName = "price_revision_id"),
		@JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id")})
	@ManyToOne(optional = false)
	private PriceCotisationEO priceCotisation;

	public SubscriptionEO() {
		subscriptionId = 0;
	}

	public SubscriptionEO(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
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

	public PriceCotisationEO getPriceCotisation() {
		return priceCotisation;
	}

	public void setPriceCotisation(PriceCotisationEO priceCotisation) {
		this.priceCotisation = priceCotisation;
	}

	public Date getDateSubscription() {
		return dateSubscription;
	}

	public void setDateSubscription(Date dateSubscription) {
		this.dateSubscription = dateSubscription;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (subscriptionId != null ? subscriptionId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SubscriptionEO)) {
			return false;
		}
		SubscriptionEO other = (SubscriptionEO) object;
		if ((this.subscriptionId == null && other.subscriptionId != null) || (this.subscriptionId != null && !this.subscriptionId.equals(other.subscriptionId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.SubscriptionEO[ subscriptionId=" + subscriptionId + " ]";
	}

}
