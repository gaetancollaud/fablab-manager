package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.SupplyTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *This is the DAO interface for a <tt>SupplyType</tt>.
 * @author Fabien Vuilleumier
 */
public interface SupplyTypeRepository extends JpaRepository<SupplyTypeEO, Integer>{

}
