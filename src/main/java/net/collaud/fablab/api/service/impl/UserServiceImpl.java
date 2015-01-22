package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import net.collaud.fablab.api.dao.GroupDao;
import net.collaud.fablab.api.dao.MembershipTypeDao;
import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.GroupEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.security.PasswordUtils;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MembershipTypeDao membershipTypeDao;

	@Autowired
	private GroupDao groupDao;

	@Override
	public UserEO findByLogin(String login) {
		if (login == null || login.isEmpty()) {
			return null;
		}
		return userDao.findByLogin(login);
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public List<UserEO> findAll() {
		return userDao.findAll();
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public Optional<UserEO> getById(Integer id) {
		return Optional.ofNullable(userDao.findOneDetails(id));
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public UserEO save(UserEO user) {
		if(user.getUserId()==null){
			user.setUserId(0);
		}
		if(user.getLogin()==null){
			user.setLogin(user.getFirstLastName());
		}
		boolean changePassword = !StringUtils.isBlank(user.getPasswordNew());
		if (changePassword) {
			user = PasswordUtils.setUseEONewPassword(user, user.getPasswordNew());
		} else if (user.getUserId() == 0) {
			//set random password for new user
			user = PasswordUtils.setUseEONewPassword(user, RandomStringUtils.random(20, true, true));
		}
		user.setMembershipType(membershipTypeDao.findOne(user.getMembershipType().getMembershipTypeId()));
		user.setGroups(new HashSet<>(groupDao.findAll(user.getGroups().stream().map(GroupEO::getGroupId).collect(toList()))));
		if (user.getUserId() > 0) {
			UserEO old = userDao.findOne(user.getUserId());
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
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public void remove(Integer id) {
		UserEO user = userDao.findOne(id);
		user.setEnabled(false);
		userDao.saveAndFlush(user);
	}

}
