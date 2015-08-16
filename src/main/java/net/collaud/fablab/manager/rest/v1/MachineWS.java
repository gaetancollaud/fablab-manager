package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.rest.v1.base.ReadRestWebservice;
import net.collaud.fablab.manager.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/machine")
@JavascriptAPIConstant("MACHINE_API")
@Slf4j
public class MachineWS extends ReadRestWebservice<MachineEO, MachineService>{

	@Autowired
	private MachineService machineService;

	@PostConstruct
	public void postConstruct(){
		super.setService(machineService);
	}

}
