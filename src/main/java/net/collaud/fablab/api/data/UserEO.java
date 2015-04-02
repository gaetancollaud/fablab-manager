package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.collaud.fablab.api.data.type.Gender;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEO extends AbstractDataEO<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Integer id;

	@Column(name = "login", unique = true)
	private String login;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	@JsonIgnore
	@Column(name = "password_salt", nullable = false)
	private String passwordSalt;

	@Transient
	@JsonProperty
	private String passwordNew;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "date_inscr", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInscr;

	@Column(name = "rfid", nullable = true)
	private String rfid;

	@Column(name = "birthdate", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Column(name = "gender", nullable = true)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	@JsonIgnore
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "phone", nullable = true)
	private String phone;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "comment", nullable = true)
	private String comment;

	@JsonManagedReference("user-subscription")
	@OneToMany(mappedBy = "user")
	private Set<SubscriptionEO> subscriptions;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<PaymentEO> payments;

	@JoinTable(name = "r_group_user",
			joinColumns = {
				@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false)},
			inverseJoinColumns = {
				@JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false, updatable = false)})
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<GroupEO> groups;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private Set<ReservationEO> reservations;

	@JoinColumn(name = "membership_type_id", referencedColumnName = "membership_type_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MembershipTypeEO membershipType;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserBalanceEO balance;

	public UserEO(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public String getFirstLastName() {
		return firstname + " " + lastname;
	}
}
