package net.collaud.fablab.manager.rest.v1;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/api/v1/machine")
@JavascriptAPIConstant("MACHINE_API")
@Slf4j
public class MachineWS extends ReadWriteRestWebservice<MachineEO, MachineService> {

    @Autowired
    private MachineService machineService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineService);
    }

}
