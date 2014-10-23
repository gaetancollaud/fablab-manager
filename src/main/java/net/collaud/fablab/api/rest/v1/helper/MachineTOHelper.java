package net.collaud.fablab.api.rest.v1.helper;

import java.util.List;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.rest.v1.data.MachineTO;
import net.collaud.fablab.api.rest.v1.data.UserTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaétan
 */
@Component
public class MachineTOHelper extends AbstractTOHelper<MachineTO, MachineEO>{

	@Override
	public MachineEO fromTO(MachineTO to) {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public MachineTO fromEO(MachineEO eo) {
		MachineTO to = new MachineTO();
		to.setMachineId(eo.getMachineId());
		to.setName(eo.getName());
		return to;
	}
	
}
