package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.MachineEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineRepository extends JpaRepository<MachineEO, Integer>{
	
	@Query("SELECT m "
			+ " FROM MachineEO m"
			+ " LEFT JOIN FETCH m.machineType mt "
			+ " LEFT JOIN FETCH mt.priceList ")
	@Override
	List<MachineEO> findAll();
}
