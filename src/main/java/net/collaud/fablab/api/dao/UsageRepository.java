package net.collaud.fablab.api.dao;

import java.lang.annotation.Repeatable;
import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.UsageDetailEO;
import net.collaud.fablab.api.data.UsageEO;
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
public interface UsageRepository extends JpaRepository<UsageEO, Integer> {

	@Query(" SELECT u "
			+ " FROM UsageEO u "
			+ " WHERE u.dateStart>=:dateAfter AND u.dateStart <=:dateBefore")
	public List<UsageDetailEO> getAllBetween(@Param("dateBefore") Date dateBefore, @Param("dateAfter") Date dateAfter);

	@Query(" SELECT u "
			+ " FROM  UsageEO u "
			+ " WHERE u.user.id=:userId")
	public List<UsageDetailEO> getByUser(@Param("userId") Integer userId);

}
