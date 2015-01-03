package net.collaud.fablab.api.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author gaetan
 */
@Entity
@Table(name = "t_usage")
@NamedQueries({
	
})
public class UsageEO extends AbstractDataEO implements Serializable {
	
	public static final String SELECT_USAGE_DETAIL = "UsageEO.selectUsageDetail";

	private static final long serialVersionUID = 1L;

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
	
	
	public UsageEO() {
	}

	public UsageEO(UserEO user, MembershipTypeEO membershipType, PriceRevisionEO priceRevision, MachineEO machine, Date dateStart, int minutes, float additionalCost, String comment) {
		this.usageId = 0;
		this.user = user;
		this.membershipType = membershipType;
		this.priceRevision = priceRevision;
		this.machine = machine;
		this.dateStart = dateStart;
		this.minutes = minutes;
		this.additionalCost = additionalCost;
		this.comment = comment;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (usageId != null ? usageId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UsageEO)) {
			return false;
		}
		UsageEO other = (UsageEO) object;
		if (this.usageId == null || other.usageId == null || !this.usageId.equals(other.usageId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "net.collaud.fablab.data.UsageEO[ utilisationId=" + usageId + " ]";
	}

}
