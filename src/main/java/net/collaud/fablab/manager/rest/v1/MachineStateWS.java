package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineStateEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.MachineStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>MachineState</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/machineState")
@JavascriptAPIConstant("MACHINE_STATE_API")
public class MachineStateWS extends ReadWriteRestWebservice<MachineStateEO, MachineStateService>{

    @Autowired
    private MachineStateService machineStateService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineStateService);
    }
}
