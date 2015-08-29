package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.MotionStockEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import net.collaud.fablab.manager.data.UserEO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>MotionStock</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface MotionStockRepository extends JpaRepository<MotionStockEO, Integer> {

    @Query("SELECT m "
            + " FROM MotionStockEO m  "
            + " LEFT JOIN FETCH m.supply  "
            + " LEFT JOIN FETCH m.user  ")
    @Override
    List<MotionStockEO> findAll();

    @Query("SELECT m "
            + " FROM MotionStockEO m "
            + " LEFT JOIN FETCH m.supply "
            + " LEFT JOIN FETCH m.user "
            + " WHERE m.id=:id")
    Optional<MotionStockEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT m "
            + " FROM MotionStockEO m "
            + " LEFT JOIN FETCH m.supply "
            + " LEFT JOIN FETCH m.user "
            + " WHERE m.motionDate>=:dateAfter AND m.motionDate <=:dateBefore")
    public List<MotionStockEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);

    @Query("SELECT m "
            + " FROM MotionStockEO m  "
            + " LEFT JOIN FETCH m.supply  "
            + " LEFT JOIN FETCH m.user  "
            + " WHERE m.user.id = :id")
    public List<MotionStockEO> findAllWithActive(@Param("id")Integer id);
}
