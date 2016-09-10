package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysema.query.annotations.QueryProjection;
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
import net.collaud.fablab.manager.data.type.Gender;
import net.collaud.fablab.manager.export.CsvExport;
import net.collaud.fablab.manager.export.CsvField;

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
@CsvExport(fileName = "users")
@JsonInclude(Include.NON_ABSENT)
public class UserEO extends AbstractDataEO<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	//password set when requesting for a new password
	@JsonIgnore
	@Column(name = "password_request")
	private String passwordRequest;

	@JsonIgnore
	@Column(name = "password_salt")
	private String passwordSalt;

	@Transient
	@JsonProperty
	private String passwordNew;

	@CsvField(headerName="Firstname")
	@Column(name = "firstname", nullable = false)
	private String firstname;

	@CsvField(headerName="Lastname")
	@Column(name = "lastname", nullable = false)
	private String lastname;

	@CsvField(headerName="Email")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@CsvField(headerName="Inscription date")
	@Column(name = "date_inscr", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInscr;

	@CsvField(headerName="Rfid")
	@Column(name = "rfid", nullable = true)
	private String rfid;

	@CsvField(headerName="birthdate")
	@Column(name = "birthdate", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@CsvField(headerName="Gender")
	@Column(name = "gender", nullable = true)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;

	@JsonIgnore
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@CsvField(headerName="Phone")
	@Column(name = "phone", nullable = true)
	private String phone;

	@CsvField(headerName="Address")
	@Column(name = "address", nullable = true)
	private String address;

	@CsvField(headerName="Comment")
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

	@CsvField(headerName="Balance")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserBalanceEO balance;

	public UserEO(Long id) {
		this.id = id;
	}
	
	

	@JsonIgnore
	public String getFirstLastName() {
		return firstname + " " + lastname;
	}

	@QueryProjection
	public UserEO(Long id, String firstname, String lastname, String email) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
}
