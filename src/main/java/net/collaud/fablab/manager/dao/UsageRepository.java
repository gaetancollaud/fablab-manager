package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.UsageEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Usage</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface UsageRepository extends JpaRepository<UsageEO, Integer> {

    @Query("SELECT u "
            + " FROM UsageEO u  "
            + " LEFT JOIN FETCH u.user  "
            + " LEFT JOIN FETCH u.cashier  "
            + " LEFT JOIN FETCH u.machine m "
            + " LEFT JOIN FETCH m.machineType "
            + " LEFT JOIN FETCH u.membershipType "
            + " WHERE u.active = true")
    @Override
    List<UsageEO> findAll();

    @Query("SELECT u "
            + " FROM UsageEO u "
            + " LEFT JOIN FETCH u.user "
            + " LEFT JOIN FETCH u.cashier "
            + " LEFT JOIN FETCH u.machine m "
            + " LEFT JOIN FETCH m.machineType "
            + " LEFT JOIN FETCH u.membershipType "
            + " WHERE u.id=:id AND u.active = true")
    Optional<UsageEO> findOneDetails(@Param("id") Integer id);

    @Query(" SELECT u "
            + " FROM UsageEO u "
            + " LEFT JOIN FETCH u.user  "
            + " LEFT JOIN FETCH u.cashier  "
            + " LEFT JOIN FETCH u.machine m "
            + " LEFT JOIN FETCH m.machineType "
            + " WHERE u.dateStart>=:dateAfter AND u.dateStart <=:dateBefore ")
    public List<UsageEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);

    @Query(" SELECT u "
            + " FROM  UsageEO u "
            + " LEFT JOIN FETCH u.user  "
            + " LEFT JOIN FETCH u.cashier  "
            + " LEFT JOIN FETCH u.machine m "
            + " LEFT JOIN FETCH m.machineType "
            + " WHERE u.user.id=:userId AND u.active = true ")
    public List<UsageEO> getByUser(@Param("userId") Integer userId);

    @Query("SELECT u "
            + " FROM UsageEO u  "
            + " LEFT JOIN FETCH u.user  "
            + " LEFT JOIN FETCH u.cashier  "
            + " LEFT JOIN FETCH u.machine m "
            + " LEFT JOIN FETCH m.machineType "
            + " LEFT JOIN FETCH u.membershipType "
            + " WHERE u.user.id = :id")
    public List<UsageEO> findAllWithActive(@Param("id")Integer id);
}