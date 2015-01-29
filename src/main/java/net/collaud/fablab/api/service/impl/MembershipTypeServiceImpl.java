package net.collaud.fablab.api.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.MembershipTypeRepository;
import net.collaud.fablab.api.data.MembershipTypeEO;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.MembershipTypeService;
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
public class MembershipTypeServiceImpl extends AbstractServiceImpl implements MembershipTypeService {

	@Autowired
	private MembershipTypeRepository membershipTypeDao;

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public List<MembershipTypeEO> findAll() {
		return membershipTypeDao.findAll();
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_USER})
	public Optional<MembershipTypeEO> getById(Integer id) {
		return Optional.ofNullable(membershipTypeDao.findOne(id));
	}
}
