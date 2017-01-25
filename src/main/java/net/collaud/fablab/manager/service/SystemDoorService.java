package net.collaud.fablab.manager.service;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.audit.AuditUtils;
import net.collaud.fablab.manager.dao.UserRepository;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.data.type.DoorAction;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.security.Roles;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Gaétan on 25/01/2017.
 */
@Slf4j
@Service
public class SystemDoorService {

	@Autowired
	private UserRepository userRepository;

	private AuditService auditService;


	@Secured({Roles.SYSTEM})
	public Map<String, String> getRfids() {
		return userRepository.findAll().stream()
				.filter(u -> !StringUtils.isEmpty(u.getRfid()))
				.collect(Collectors.toMap(u -> u.getFirstLastName(), u -> u.getRfid()));
	}

	@Secured({Roles.SYSTEM})
	public void event(DoorAction action, String rfid) {
		StringBuilder sb = new StringBuilder();

		Optional<UserEO> user = Optional.empty();
		if (rfid != null) {
			user = userRepository.findByRFID(rfid);
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

		LOG.info(sb.toString());
		try {
			AuditUtils.addAudit(auditService, user.orElse(null), AuditObject.ACCESS_DOOR, AuditAction.UPDATE, success, sb.toString());
		} catch (FablabException ex) {
			LOG.error("Cannot add audit entry");
		}
	}

}
