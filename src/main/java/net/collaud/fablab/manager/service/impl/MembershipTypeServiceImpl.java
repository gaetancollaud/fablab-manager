package net.collaud.fablab.manager.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.dao.MembershipTypeRepository;
import net.collaud.fablab.manager.data.MembershipTypeEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.MembershipTypeService;
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
public class MembershipTypeServiceImpl extends AbstractServiceImpl implements MembershipTypeService {

	@Autowired
	private MembershipTypeRepository membershipTypeRepository;

	@Override
	public List<MembershipTypeEO> findAll() {
		return membershipTypeRepository.findAll();
	}

	@Override
	@Secured({Roles.USER_MANAGE})
	public Optional<MembershipTypeEO> getById(Long id) {
		return Optional.ofNullable(membershipTypeRepository.findOne(id));
	}
}
