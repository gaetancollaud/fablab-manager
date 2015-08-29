package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.SupplyTypeEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.SupplyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>SupplyType</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/supplyType")
@JavascriptAPIConstant("SUPPLY_TYPE_API")
public class SupplyTypeWS extends ReadWriteRestWebservice<SupplyTypeEO, SupplyTypeService>{

    @Autowired
    private SupplyTypeService supplyTypeService;

    @PostConstruct
    public void postConstruct(){
        super.setService(supplyTypeService);
    }
}

