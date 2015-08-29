package net.collaud.fablab.manager.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineTypeEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.MachineTypeService;
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
@RequestMapping("/v1/machineType")
@JavascriptAPIConstant("MACHINE_TYPE_API")
public class MachineTypeWS extends ReadWriteRestWebservice<MachineTypeEO, MachineTypeService>{

    @Autowired
    private MachineTypeService machineTypeService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineTypeService);
    }
    
    @RequestMapping(value = "getPrices", method = RequestMethod.GET)
    public List<PriceMachineEO> getPrices(@RequestParam(value = "id") Integer id) {
        return machineTypeService.getPrices(id);
    }
    
    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public MachineTypeEO getId(@RequestParam(value = "technicalname") String technicalname) {
        return machineTypeService.getId(technicalname);
    }
}
