package net.collaud.fablab.api.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.MembershipTypeRepository;
import net.collaud.fablab.api.data.MembershipTypeEO;
import net.collaud.fablab.api.security.Roles;
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
@Secured({Roles.ADMIN})
public class MembershipTypeServiceImpl extends AbstractServiceImpl implements MembershipTypeService {

	@Autowired
	private MembershipTypeRepository membershipTypeDao;

	@Override
	@Secured({Roles.USER_MANAGE})
	public List<MembershipTypeEO> findAll() {
		return membershipTypeDao.findAll();
	}

	@Override
	@Secured({Roles.USER_MANAGE})
	public Optional<MembershipTypeEO> getById(Integer id) {
		return Optional.ofNullable(membershipTypeDao.findOne(id));
	}
}
