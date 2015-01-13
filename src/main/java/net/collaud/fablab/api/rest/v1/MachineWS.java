package net.collaud.fablab.api.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.dao.MachineDao;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.rest.v1.base.ReadRestWebservice;
import net.collaud.fablab.api.security.RolesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/machine")
@Secured({RolesHelper.ROLE_ADMIN})
@Slf4j
public class MachineWS extends ReadRestWebservice<MachineEO, MachineDao>{

	@Autowired
	private MachineDao machineDao;

	@PostConstruct
	public void postConstruct(){
		super.setRepository(machineDao);
	}

}
