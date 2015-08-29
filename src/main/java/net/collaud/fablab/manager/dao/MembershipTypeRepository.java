package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.MembershipTypeEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>MembershipType</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface MembershipTypeRepository extends JpaRepository<MembershipTypeEO, Integer> {

    @Query("SELECT DISTINCT m "
            + " FROM MembershipTypeEO m")
    @Override
    List<MembershipTypeEO> findAll();

    @Query("SELECT m.priceList "
            + " FROM MembershipTypeEO m "
            + " WHERE m.id = :id")
    List<PriceMachineEO> getPrices(@Param("id")Integer id);

    @Query("SELECT mst "
            + " FROM MembershipTypeEO mst "
            + " WHERE UPPER(mst.name) = UPPER(:name) ")
    MembershipTypeEO getId(@Param("name") String name);

}
