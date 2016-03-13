package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineTypeEO;
import net.collaud.fablab.manager.rest.v1.base.ReadRestWebservice;
import net.collaud.fablab.manager.service.MachineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/api/v1/machine-type")
@JavascriptAPIConstant("MACHINE_TYPE_API")
@Slf4j
public class MachineTypeWS extends ReadRestWebservice<MachineTypeEO, MachineTypeService>{

	@Autowired
	private MachineTypeService machineType;

	@PostConstruct
	public void postConstruct(){
		super.setService(machineType);
	}

}
