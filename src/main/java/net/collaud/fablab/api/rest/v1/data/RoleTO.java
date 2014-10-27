package net.collaud.fablab.api.rest.v1.data;

/**
 *
 * @author Gaétan
 */
public class RoleTO extends AbstractTO {

	private Integer roleId;
	private String name;
	private String technicalname;

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
