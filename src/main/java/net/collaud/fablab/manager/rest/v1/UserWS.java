package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.UserService;
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
@RequestMapping("/v1/user")
@JavascriptAPIConstant("USER_API")
public class UserWS extends ReadWriteRestWebservice<UserEO, UserService> {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void postConstruct() {
        super.setService(userService);
    }

    @RequestMapping(value = "updateMailingList", method = RequestMethod.GET)
    public void updateMailingList() {
        userService.updateMailingList();
    }

    @RequestMapping(value = "canUse", method = RequestMethod.GET)
    public boolean canUse(@RequestParam("machineTypeId") Integer machineTypeId,
            @RequestParam("userId") Integer userId) {
        return userService.canUse(machineTypeId, userId);
    }
    
    @RequestMapping(value = "hasRole", method = RequestMethod.GET)
    public boolean hasRole(@RequestParam("userId") Integer userId,
            @RequestParam("role") String role) {
        return userService.hasRole(userId, role);
    }
    
    @RequestMapping(value = "balance", method = RequestMethod.GET)
    public Double balance(@RequestParam("userId") Integer userId) {
        return userService.balance(userId);
    }
}
