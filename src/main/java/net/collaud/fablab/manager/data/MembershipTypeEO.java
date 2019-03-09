package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_membership_type")
@Getter
@Setter
@ToString(exclude = {"priceList"})
public class MembershipTypeEO extends AbstractDataEO<Long> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "membership_type_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "duration", nullable = false)
	private Integer duration;

	@Column(name = "price", nullable = false)
	private Double price;

	@JsonManagedReference("membershipType-pice")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipType", fetch = FetchType.LAZY)
	private Set<PriceMachineEO> priceList;

}
