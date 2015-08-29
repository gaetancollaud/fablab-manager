package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.MachineStatusEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *This is the DAO interface for a <tt>MachineStatus</tt>.
 * @author Fabien Vuilleumier
 */
public interface MachineStatusRepository extends JpaRepository<MachineStatusEO, Integer>{

    @Query("SELECT ms "
            + " FROM MachineStatusEO ms "
            + " WHERE UPPER(ms.label) = UPPER(:label)")
    MachineStatusEO getByLabel(@Param("label") String label);

}
