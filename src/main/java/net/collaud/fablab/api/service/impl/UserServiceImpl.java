package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.dao.GroupRepository;
import net.collaud.fablab.api.dao.MembershipTypeRepository;
import net.collaud.fablab.api.dao.UserRepository;
import net.collaud.fablab.api.data.GroupEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.security.PasswordUtils;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.MailService;
import net.collaud.fablab.api.service.UserService;
import net.collaud.fablab.api.service.util.recaptcha.ReCaptchaChecker;
import net.collaud.fablab.api.service.util.recaptcha.ReCaptchaCheckerReponse;
import net.collaud.fablab.api.web.SpringPropertiesUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

	public static final String PROP_RECAPTCHA_SITE = "google.recaptcha.site";
	public static final String PROP_RECAPTCHA_SECRET = "google.recaptcha.secret";

	@Autowired
	private MailService mailService;

	@Autowired
	private UserRepository userDao;

	@Autowired
	private MembershipTypeRepository membershipTypeDao;

	@Autowired
	private GroupRepository groupDao;

	@Autowired
	private SpringPropertiesUtils propertyUtils;

	@Override
	public UserEO findByLogin(String login) {
		if (login == null || login.isEmpty()) {
			return null;
		}
		return userDao.findByLogin(login);
	}

	@Override
	@Secured({Roles.USER_MANAGE})
	public List<UserEO> findAll() {
		return userDao.findAll();
	}

	@Override
	@Secured({Roles.USER_MANAGE})
	public Optional<UserEO> getById(Integer id) {
		return Optional.ofNullable(userDao.findOneDetails(id));
	}

	@Override
	@Secured({Roles.USER_MANAGE})
	public UserEO save(UserEO user) {
		if (user.getId() == null) {
			user.setId(0);
		}
		boolean changePassword = !StringUtils.isBlank(user.getPasswordNew());
		if (changePassword) {
			user = PasswordUtils.setUseEONewPassword(user, user.getPasswordNew());
		} else if (user.getId() == 0) {
			//set random password for new user
			user = PasswordUtils.setUseEONewPassword(user, RandomStringUtils.random(20, true, true));
		}
		user.setMembershipType(membershipTypeDao.findOne(user.getMembershipType().getId()));
		if (user.getGroups() != null) {
			user.setGroups(new HashSet<>(groupDao.findAll(user.getGroups().stream().map(GroupEO::getId).collect(toList()))));
		}
		if (user.getId() > 0) {
			UserEO old = userDao.findOne(user.getId());
			old.setFirstname(user.getFirstname());
			old.setLastname(user.getLastname());
			old.setEmail(user.getEmail());
			old.setPhone(user.getPhone());
			old.setAddress(user.getAddress());
			old.setRfid(user.getRfid());
			old.setMembershipType(user.getMembershipType());
			old.setGroups(user.getGroups());
			if (changePassword) {
				old.setPassword(user.getPassword());
				old.setPasswordSalt(user.getPasswordSalt());
			}
			return userDao.saveAndFlush(old);
		} else {
			user.setEnabled(true);
			user.setDateInscr(new Date());
			return userDao.saveAndFlush(user);
		}
	}

	@Override
	@Secured({Roles.USER_MANAGE})
	public void remove(Integer id) {
		UserEO user = userDao.findOne(id);
		user.setEnabled(false);
		userDao.saveAndFlush(user);
	}

	@Override
	public void signup(UserEO user, String recaptcha) {
		String secret = propertyUtils.getProperty(PROP_RECAPTCHA_SECRET).orElse("");
		ReCaptchaCheckerReponse rep = ReCaptchaChecker.checkReCaptcha(secret, recaptcha);
		if (rep.isSuccess()) {
			save(user);
			Map<String, Object> scope = new HashMap<>();
			scope.put("email", user.getEmail());
			mailService.sendMail("Inscription", MailServiceImpl.Template.SIGNUP, scope, user.getEmail());
		} else {
			log.error("Recaptcha check failed because of " + rep.getErrorCodes());
		}
	}

}
