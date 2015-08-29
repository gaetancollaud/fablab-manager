package net.collaud.fablab.manager.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MembershipTypeEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.MembershipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>MembershipType</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/membershipType")
@JavascriptAPIConstant("MEMBERSHIP_TYPE_API")
public class MembershipTypeWS extends ReadWriteRestWebservice<MembershipTypeEO, MembershipTypeService>{

    @Autowired
    private MembershipTypeService membershipTypeService;

    @PostConstruct
    public void postConstruct() {
        super.setService(membershipTypeService);
    }

    @RequestMapping(value = "getPrices", method = RequestMethod.GET)
    public List<PriceMachineEO> getPrices(@RequestParam(value = "id") Integer id) {
        return membershipTypeService.getPrices(id);
    }
    
    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public MembershipTypeEO getId(@RequestParam(value = "name") String name) {
        return membershipTypeService.getId(name);
    }

}
