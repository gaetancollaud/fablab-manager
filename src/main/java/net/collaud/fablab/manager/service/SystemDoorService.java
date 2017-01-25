package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.dao.UserRepository;
import net.collaud.fablab.manager.security.Roles;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Gaétan on 25/01/2017.
 */
@Service
public class SystemDoorService {

	@Autowired
	private UserRepository userRepository;


	@Secured({Roles.SYSTEM})
	public Map<String, String> getRfids() {
		return userRepository.findAll().stream()
				.filter(u -> !StringUtils.isEmpty(u.getRfid()))
				.collect(Collectors.toMap(u -> u.getFirstLastName(), u -> u.getRfid()));
	}
}
