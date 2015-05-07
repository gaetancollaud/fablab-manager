package net.collaud.fablab.api.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_price_revision")
@Getter
@Setter
@ToString
public class PriceRevisionEO extends AbstractDataEO<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "price_revision_id", nullable = false)
	private Integer id;

	@Column(name = "date_revision", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRevision;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "priceRevision", fetch = FetchType.LAZY)
	private List<PriceMachineEO> priceMachineList;

	@Column(name = "membership_duration", nullable = false)
	private int membershipDuration;

}
