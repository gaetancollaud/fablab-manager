package net.collaud.fablab.api.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.GroupDao;
import net.collaud.fablab.api.data.GroupEO;
import net.collaud.fablab.api.security.RolesHelper;
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
@Secured({RolesHelper.ROLE_ADMIN})
public class GroupServiceImpl extends AbstractServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public List<GroupEO> findAll() {
		return groupDao.findAllWithRoles();
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public Optional<GroupEO> getById(Integer id) {
		return Optional.ofNullable(groupDao.findOne(id));
	}
}
