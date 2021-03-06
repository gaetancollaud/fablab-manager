package net.collaud.fablab.manager.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.GroupRepository;
import net.collaud.fablab.manager.data.GroupEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.GroupService;
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
	public Optional<GroupEO> getById(Long id) {
		return Optional.ofNullable(groupDao.findOne(id));
	}
}
