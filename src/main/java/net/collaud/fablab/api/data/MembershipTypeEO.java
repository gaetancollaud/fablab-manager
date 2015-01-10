package net.collaud.fablab.api.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_membership_type")
@NamedQueries({
	@NamedQuery(name = MembershipTypeEO.FIND_BY_NAME,
			query = "SELECT mt FROM MembershipTypeEO mt WHERE mt.name=:" + MembershipTypeEO.PARAM_NAME)
})
public class MembershipTypeEO extends AbstractDataEO implements Serializable {

	public static final String FIND_BY_NAME = "MembershipTypeEO.selectbyname";
	public static final String PARAM_NAME = "name";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "membership_type_id", nullable = false)
	private Integer membershipTypeId;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipType", fetch = FetchType.LAZY)
	private List<PriceMachineEO> priceList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipType", fetch = FetchType.LAZY)
	private List<UserEO> userList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "membershipType", fetch = FetchType.LAZY)
	private List<PriceCotisationEO> priceCotisationList;

	public MembershipTypeEO() {
	}

	public MembershipTypeEO(Integer membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}

	public MembershipTypeEO(Integer membershipTypeId, String name) {
		this.membershipTypeId = membershipTypeId;
		this.name = name;
	}

	public Integer getMembershipTypeId() {
		return membershipTypeId;
	}

	public void setMembershipTypeId(Integer membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PriceMachineEO> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<PriceMachineEO> priceList) {
		this.priceList = priceList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (membershipTypeId != null ? membershipTypeId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MembershipTypeEO)) {
			return false;
		}
		MembershipTypeEO other = (MembershipTypeEO) object;
		if (this.membershipTypeId == null || other.membershipTypeId == null || !this.membershipTypeId.equals(other.membershipTypeId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public List<UserEO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEO> userList) {
		this.userList = userList;
	}

	public List<PriceCotisationEO> getPriceCotisationList() {
		return priceCotisationList;
	}

	public void setPriceCotisationList(List<PriceCotisationEO> priceCotisationList) {
		this.priceCotisationList = priceCotisationList;
	}

}
