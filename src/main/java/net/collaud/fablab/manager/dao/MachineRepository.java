package net.collaud.fablab.manager.dao;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.data.MachineEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineRepository extends JpaRepository<MachineEO, Integer> {

    @Query("SELECT DISTINCT m "
            + " FROM MachineEO m"
            + " LEFT JOIN FETCH m.machineType "
            + " LEFT JOIN FETCH m.machineStatus "
            + " LEFT JOIN FETCH m.machineState ")
    @Override
    List<MachineEO> findAll();
    
   
    @Query("SELECT m "
            + " FROM MachineEO m"
            + " LEFT JOIN FETCH m.machineType "
            + " LEFT JOIN FETCH m.machineStatus "
            + " LEFT JOIN FETCH m.machineState "
            + " WHERE m.id=:id")
    Optional<MachineEO> findOneDetails(@Param("id")Integer id);
    
   @Query("SELECT m "
            + " FROM MachineEO m"
            + " LEFT JOIN FETCH m.machineType "
            + " LEFT JOIN FETCH m.machineStatus mst"
            + " WHERE mst.label = :label ")
    List<MachineEO> getByStatusLabel(@Param("label")String label);
    
    @Query("SELECT m "
            + " FROM MachineEO m "
            + " WHERE m.code = :code ")
    MachineEO findSimpleByCode(@Param("code")String code);
}
