package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.GroupEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Group</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/group")
@JavascriptAPIConstant("GROUP_API")
public class GroupWS extends ReadWriteRestWebservice<GroupEO, GroupService> {

    @Autowired
    private GroupService groupService;

    @PostConstruct
    public void postConstruct() {
        super.setService(groupService);
    }
    
    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public GroupEO getId(@RequestParam(value = "technicalname") String technicalname) {
        return groupService.getId(technicalname);
    }
}
