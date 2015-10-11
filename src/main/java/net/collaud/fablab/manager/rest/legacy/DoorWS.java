package net.collaud.fablab.manager.rest.legacy;

import java.util.Optional;
import javax.annotation.security.RunAs;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.audit.AuditUtils;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.data.type.DoorAction;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.AuditService;
import net.collaud.fablab.manager.service.ConfigurationService;
import net.collaud.fablab.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/door")
@JavascriptAPIConstant("DOOR_API")
@RunAs(Roles.SYSTEM)
@Slf4j
public class DoorWS {

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuditService auditService;

	@RequestMapping(value = "event", method = RequestMethod.GET)
	public void getallMembershipType(
			@RequestParam("eventAction") DoorAction action,
			@RequestParam("rfid") String rfid,
			@RequestParam("token") String token) {
		StringBuilder sb = new StringBuilder();

		Optional<UserEO> user = Optional.empty();
		if (rfid != null) {
			user = userService.findByRFID(rfid);
			sb.append(user.map(u -> u.getFirstLastName()).orElse("Anonymous"));
			sb.append(" with RFID ").append(rfid).append(" ");
		} else {
			sb.append("Someone ");
		}
		boolean success = true;
		if (action
				!= null) {
			switch (action) {
				case OPEN:
					sb.append("opened the door");
					break;
				case CLOSE:
					sb.append("closed the door");
					break;
				case TRY_OPEN_BUT_FAIL:
					sb.append("tried to open the door but failed");
					success = false;
					break;
			}
		} else {
			sb.append("did something with the door");
		}

		log.info(sb.toString());
		try {
			AuditUtils.addAudit(auditService, user.orElse(null), AuditObject.ACCESS_DOOR, AuditAction.UPDATE, success, sb.toString());
		} catch (FablabException ex) {
			log.error("Cannot add audit entry");
		}
	}

}
