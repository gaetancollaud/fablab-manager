package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.MachineEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineRepository extends JpaRepository<MachineEO, Long>{
	
	@Query("SELECT m "
			+ " FROM MachineEO m"
			+ " LEFT JOIN FETCH m.machineType mt "
			+ " LEFT JOIN FETCH mt.priceList ")
	@Override
	List<MachineEO> findAll();
}
