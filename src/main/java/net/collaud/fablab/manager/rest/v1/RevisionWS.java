package net.collaud.fablab.manager.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.RevisionEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Revision</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/revision")
@JavascriptAPIConstant("REVISION_API")
public class RevisionWS extends ReadWriteRestWebservice<RevisionEO, RevisionService>{

    @Autowired
    private RevisionService revisionService;
    @PostConstruct
    public void postConstruct() {
        super.setService(revisionService);
    }
        
   @RequestMapping(value = "listByMachine", method = RequestMethod.GET)
    public List<RevisionEO> listByMachine(@RequestParam(value = "id") Integer id) {
        return revisionService.listByMachine(id);
    }
}
