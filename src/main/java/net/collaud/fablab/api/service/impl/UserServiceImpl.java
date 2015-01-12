package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.HashSet;
import static java.util.stream.Collectors.toList;
import net.collaud.fablab.api.dao.GroupDao;
import net.collaud.fablab.api.dao.MembershipTypeDao;
import net.collaud.fablab.api.dao.UserDao;
import net.collaud.fablab.api.data.GroupEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.security.PasswordUtils;
import net.collaud.fablab.api.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Iterable<UserEO> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public UserEO findById(Integer id) {
		return userDao.findOneDetails(id);
	}

	@Override
	public UserEO save(UserEO user) {
		boolean changePassword = !StringUtils.isBlank(user.getNewPassword());
		if(changePassword){
			user = PasswordUtils.setUseEONewPassword(user, user.getNewPassword());
		}else if(user.getUserId()==0){
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
			if(changePassword){
				old.setPassword(user.getPassword());
			}
			return userDao.saveAndFlush(old);
		} else {
			user.setDateInscr(new Date());
			return userDao.saveAndFlush(user);
		}
	}

	@Override
	public void removeById(Integer id) {
		UserEO user = userDao.findOne(id);
		user.setEnabled(false);
		userDao.saveAndFlush(user);
	}

}
