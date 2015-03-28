package net.collaud.fablab.api.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEO, Integer>{

	@Query(" SELECT s "
			+ " FROM SubscriptionEO s "
			+ " WHERE s.dateSubscription>=:dateAfter AND s.dateSubscription <=:dateBefore")
	public List<SubscriptionEO> getAllBetween(@Param("dateBefore") Date dateBefore, @Param("dateAfter") Date dateAfter);

	@Query(" SELECT s "
			+ " FROM  SubscriptionEO s "
			+ " WHERE s.user.id=:userId")
	public List<SubscriptionEO> getByUser(@Param("userId") Integer user);
	
}