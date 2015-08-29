package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.SubscriptionEO;
import net.collaud.fablab.manager.data.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEO, Integer> {

    @Query("SELECT s "
            + " FROM SubscriptionEO s "
            + " LEFT JOIN FETCH s.user "
            + " WHERE s.active = true ")
    @Override
    List<SubscriptionEO> findAll();

    @Query(" SELECT s "
            + " FROM SubscriptionEO s "
            + " LEFT JOIN FETCH s.user "
            + " WHERE s.dateSubscription>=:dateAfter AND s.dateSubscription <=:dateBefore")
    public List<SubscriptionEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);

    @Query(" SELECT s "
            + " FROM  SubscriptionEO s "
            + " LEFT JOIN FETCH s.user "
            + " WHERE s.user.id=:userId AND s.active = true")
    public List<SubscriptionEO> getByUser(@Param("userId") Integer user);

    @Query(" SELECT s "
            + " FROM  SubscriptionEO s "
            + " LEFT JOIN FETCH s.user "
            + " WHERE s.user.id = :id")
    public List<SubscriptionEO> findAllWithActive(@Param("id") Integer id);

}
