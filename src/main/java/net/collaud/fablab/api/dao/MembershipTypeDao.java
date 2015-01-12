package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.MembershipTypeEO;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MembershipTypeDao {

	List<MembershipTypeEO> getAllMembershipType();
}
