package net.collaud.fablab.api.dao.impl;

import java.util.List;
import net.collaud.fablab.api.dao.MembershipTypeDao;
import net.collaud.fablab.api.data.MembershipTypeEO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
@Transactional
public class MembershipTypeDaoImpl extends AbstractDAO<MembershipTypeEO> implements MembershipTypeDao {

	public MembershipTypeDaoImpl() {
		super(MembershipTypeEO.class);
	}

	@Override
	public List<MembershipTypeEO> getAllMembershipType() {
		return super.findAllEntities();
	}

}
