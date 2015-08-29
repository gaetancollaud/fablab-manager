package net.collaud.fablab.manager.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.SupplyEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Supply</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/supply")
@JavascriptAPIConstant("SUPPLY_API")
public class SupplyWS extends ReadWriteRestWebservice<SupplyEO, SupplyService> {

    @Autowired
    private SupplyService supplyService;

    @PostConstruct
    public void postConstruct() {
        super.setService(supplyService);
    }

    @RequestMapping(value = "stock", method = RequestMethod.GET)
    public List<SupplyEO> stock() throws FablabException {
        return supplyService.stock();
    }

    @RequestMapping(value = "addQuantity", method = RequestMethod.GET)
    public void stock(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "quantity") Float quantity) throws FablabException {
        supplyService.addQuantity(id, quantity);
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public SupplyEO getById(@RequestParam(value = "id") Integer id) {
        return supplyService.getById(id).get();
    }
}
