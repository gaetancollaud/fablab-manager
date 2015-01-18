package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.rest.v1.base.ReadRestWebservice;
import net.collaud.fablab.api.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/machine")
@Slf4j
public class MachineWS extends ReadRestWebservice<MachineEO, MachineService>{

	@Autowired
	private MachineService machineService;

	@PostConstruct
	public void postConstruct(){
		super.setService(machineService);
	}

}
