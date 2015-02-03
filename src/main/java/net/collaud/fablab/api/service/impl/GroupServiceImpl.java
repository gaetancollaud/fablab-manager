package net.collaud.fablab.api.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.GroupRepository;
import net.collaud.fablab.api.data.GroupEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.GroupService;
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
@Secured({Roles.ADMIN})
public class GroupServiceImpl extends AbstractServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupDao;

	@Override
	@Secured({Roles.USER_MANAGE})
	public List<GroupEO> findAll() {
		return groupDao.findAllWithRoles();
	}

	@Override
	@Secured({Roles.USER_MANAGE})
	public Optional<GroupEO> getById(Integer id) {
		return Optional.ofNullable(groupDao.findOne(id));
	}
}
