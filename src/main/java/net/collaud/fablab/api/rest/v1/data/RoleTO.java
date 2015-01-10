package net.collaud.fablab.api.rest.v1.data;

import net.collaud.fablab.api.data.RoleEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
public class RoleTO extends AbstractTO<RoleEO, RoleTO> {

	private Integer roleId;
	private String name;
	private String technicalname;
	
	@Override
	public RoleEO convertToEO() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public RoleTO fromEO(RoleEO eo) {
		setRoleId(eo.getRoleId());
		setName(eo.getName());
		setTechnicalname(eo.getTechnicalname());
		return this;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTechnicalname() {
		return technicalname;
	}

	public void setTechnicalname(String technicalname) {
		this.technicalname = technicalname;
	}

}
