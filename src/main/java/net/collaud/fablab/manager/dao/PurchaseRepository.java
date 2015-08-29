package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.PurchaseEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import net.collaud.fablab.manager.data.UserEO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Purchase</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface PurchaseRepository extends JpaRepository<PurchaseEO, Integer> {

    @Query("SELECT p "
            + " FROM PurchaseEO p  "
            + " LEFT JOIN FETCH p.supply sup "
            + " LEFT JOIN FETCH sup.supplyType "
            + " LEFT JOIN FETCH sup.supplyUnity "
            + " LEFT JOIN FETCH p.user "
            + " WHERE p.active = true")
    @Override
    List<PurchaseEO> findAll();

    @Query("SELECT p "
            + " FROM PurchaseEO p "
            + " LEFT JOIN FETCH p.supply sup "
            + " LEFT JOIN FETCH sup.supplyType "
            + " LEFT JOIN FETCH sup.supplyUnity "
            + " LEFT JOIN FETCH p.user "
            + " WHERE p.id=:id AND p.active = true")
    Optional<PurchaseEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT p "
            + " FROM PurchaseEO p "
            + " LEFT JOIN FETCH p.supply sup "
            + " LEFT JOIN FETCH sup.supplyType "
            + " LEFT JOIN FETCH sup.supplyUnity "
            + " LEFT JOIN FETCH p.user "
            + " WHERE p.purchaseDate>=:dateAfter AND p.purchaseDate <=:dateBefore")
    public List<PurchaseEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);

    @Query("SELECT p "
            + " FROM PurchaseEO p  "
            + " LEFT JOIN FETCH p.supply sup "
            + " LEFT JOIN FETCH sup.supplyType "
            + " LEFT JOIN FETCH sup.supplyUnity "
            + " LEFT JOIN FETCH p.user "
            + " WHERE p.user.id = :id")
    public List<PurchaseEO> findAllWithActive(@Param("id") Integer id);
}
