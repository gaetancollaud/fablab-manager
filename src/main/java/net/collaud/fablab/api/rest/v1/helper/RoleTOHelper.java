package net.collaud.fablab.api.rest.v1.helper;

import net.collaud.fablab.api.data.RoleEO;
import net.collaud.fablab.api.rest.v1.data.RoleTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaétan
 */
@Component
public class RoleTOHelper extends AbstractTOHelper<RoleTO, RoleEO>{

	@Override
	public RoleEO fromTO(RoleTO to) {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public RoleTO fromEO(RoleEO eo) {
		RoleTO to = new RoleTO();
		to.setRoleId(eo.getRoleId());
		to.setName(eo.getName());
		to.setTechnicalname(eo.getTechnicalname());
		return to;
	}

	
}
