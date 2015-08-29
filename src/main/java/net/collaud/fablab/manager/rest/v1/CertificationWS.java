package net.collaud.fablab.manager.rest.v1;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.CertificationEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.CertificationService;
import net.collaud.fablab.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Certification</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/certification")
@JavascriptAPIConstant("CERTIFICATION_API")
public class CertificationWS extends ReadWriteRestWebservice<CertificationEO, CertificationService> {

    @Autowired
    private CertificationService certificationService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void postConstruct() {
        super.setService(certificationService);
    }

    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public CertificationEO getId(@RequestParam(value = "name") String name) {
        return certificationService.getId(name);
    }

    @RequestMapping(value = "canCertify", method = RequestMethod.GET)
    public boolean canCertify(@RequestParam(value = "certificationId") Integer certificationId,
            @RequestParam(value = "userId") Integer userId) {
        return certificationService.canCertify(certificationId, userId);
    }

    @RequestMapping(value = "failedUser", method = RequestMethod.GET)
    public List<String> failedUser(@RequestParam(value = "certificationId") Integer certificationId,
            @RequestParam(value = "userIds") List<Integer> userIds) {
        List<String> names = new ArrayList<>();
        for (Integer id : certificationService.failedUser(certificationId, userIds)) {
            UserEO u = userService.getById(id).get();
            if (u != null) {
                StringBuilder str = new StringBuilder();
                str.append(u.getLastname().toUpperCase());
                str.append(" ").append(u.getFirstname());
                names.add(str.toString());
            }
        }
        return names;
    }
}
