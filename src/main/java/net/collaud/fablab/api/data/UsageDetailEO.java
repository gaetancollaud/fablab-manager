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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "v_usage_detail")
@Immutable
@Getter
@Setter
@ToString
public class UsageDetailEO extends AbstractDataEO<Integer> implements Serializable {

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
	private Integer id;

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

	@Column(name = "price", nullable = false)
	private float price;

}
