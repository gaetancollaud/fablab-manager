package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.UsageEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
public interface UsageRepository extends JpaRepository<UsageEO, Long> {

	@Query(" SELECT u "
			+ " FROM UsageEO u "
			+ " WHERE u.dateStart>=:dateAfter AND u.dateStart <=:dateBefore")
	public List<UsageEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);

	@Query(" SELECT u "
			+ " FROM  UsageEO u "
			+ " WHERE u.user.id=:userId ")
	public List<UsageEO> getByUser(@Param("userId") Long userId);

}
