package net.collaud.fablab.manager.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.MachineStatusEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud
 * <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/machine")
@JavascriptAPIConstant("MACHINE_API")
public class MachineWS extends ReadWriteRestWebservice<MachineEO, MachineService> {

    @Autowired
    private MachineService machineService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineService);
    }

    @RequestMapping(value = "getByStatusLabel", method = RequestMethod.GET)
    public List<MachineEO> getByStatusLabel(@RequestParam(value = "label") String label) {
        return machineService.getByStatusLabel(label);
    }

    @RequestMapping(value = "findSimpleByCode", method = RequestMethod.GET)
    public MachineEO findSimpleByCode(@RequestParam(value = "code") String code) {
        return machineService.findSimpleByCode(code);
    }

    @RequestMapping(value = "saveStatus", method = RequestMethod.GET)
    public MachineStatusEO saveStatus(@RequestParam(value = "machineId") Integer machineId,
            @RequestParam(value = "machineStatusId") Integer machineStatusId) {
        return machineService.saveStatus(machineId, machineStatusId);
    }
}
