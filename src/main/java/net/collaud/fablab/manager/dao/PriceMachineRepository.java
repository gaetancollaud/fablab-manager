package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.data.PriceMachineEOPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface PriceMachineRepository extends JpaRepository<PriceMachineEO, PriceMachineEOPK>{
	
}
