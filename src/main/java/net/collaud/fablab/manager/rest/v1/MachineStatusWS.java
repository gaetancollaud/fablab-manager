package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineStatusEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.MachineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>MachineStatus</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/machineStatus")
@JavascriptAPIConstant("MACHINE_STATUS_API")
public class MachineStatusWS extends ReadWriteRestWebservice<MachineStatusEO, MachineStatusService>{

    @Autowired
    private MachineStatusService machineStatusService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineStatusService);
    }
    
    @RequestMapping(value="getByLabel", method = RequestMethod.GET)
    public MachineStatusEO getByLabel(@RequestParam("label") String label){
        return machineStatusService.getByLabel(label);
    }
    
}
