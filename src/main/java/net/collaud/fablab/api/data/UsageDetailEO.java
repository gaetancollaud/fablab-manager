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
import org.hibernate.annotations.Immutable;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "v_usage_detail")
@Immutable
@NamedQueries({
	@NamedQuery(name = UsageDetailEO.SELECT_FROM_DATES,
			query = "SELECT u FROM UsageDetailEO u "
			+ " JOIN FETCH u.user"
			+ " WHERE u.dateStart<=:" + UsageDetailEO.PARAM_DATE_BEFORE
			+ " AND u.dateStart>=:" + UsageDetailEO.PARAM_DATE_AFTER),
	@NamedQuery(name = UsageDetailEO.SELECT_FROM_USER,
			query = "SELECT u FROM UsageDetailEO u WHERE u.user=:" + UsageDetailEO.PARAM_USER),
	@NamedQuery(name = UsageDetailEO.SELECT_FROM_IDS,
			query = "SELECT u FROM UsageDetailEO u WHERE u.usageId IN :" + UsageDetailEO.PARAM_IDS),})
public class UsageDetailEO extends AbstractDataEO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SELECT_FROM_USER = "UsageEO.selectFromUser";
	public static final String SELECT_FROM_DATES = "UsageEO.selectFromDates";
	public static final String SELECT_FROM_IDS = "UsageEO.findByIds";

	public static final String PARAM_IDS = "ids";
	public static final String PARAM_USER = "user";
	public static final String PARAM_DATE_BEFORE = "dateBefore";
	public static final String PARAM_DATE_AFTER = "dateAfter";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usage_id", nullable = false)
	private Integer usageId;

	@Column(name = "date_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;

	@Column(name = "minutes")
	private int minutes;

	@Column(name = "additional_cost", nullable = false)
	private float additionalCost;

	@Size(max = 255)
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

	@JoinColumn(name = "price_revision_id", referencedColumnName = "price_revision_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private PriceRevisionEO priceRevision;

	@Column(name = "price", nullable = false)
	private float price;

	public UsageDetailEO() {
	}

	public Integer getUsageId() {
		return usageId;
	}

	public void setUsageId(Integer usageId) {
		this.usageId = usageId;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public float getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(float additionalCost) {
		this.additionalCost = additionalCost;
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

	public MachineEO getMachine() {
		return machine;
	}

	public void setMachine(MachineEO machine) {
		this.machine = machine;
	}

	public MembershipTypeEO getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipTypeEO membershipType) {
		this.membershipType = membershipType;
	}

	public PriceRevisionEO getPriceRevision() {
		return priceRevision;
	}

	public void setPriceRevision(PriceRevisionEO priceRevision) {
		this.priceRevision = priceRevision;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
