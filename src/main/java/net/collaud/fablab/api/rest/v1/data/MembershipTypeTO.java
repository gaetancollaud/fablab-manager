package net.collaud.fablab.api.rest.v1.data;

import net.collaud.fablab.api.data.MembershipTypeEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
public class MembershipTypeTO extends AbstractTO<MembershipTypeEO, MembershipTypeTO> {

	private Integer id;
	private String name;
	
	@Override
	public MembershipTypeEO convertToEO() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public MembershipTypeTO fromEO(MembershipTypeEO eo) {
		setId(eo.getMembershipTypeId());
		setName(eo.getName());
		return this;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
