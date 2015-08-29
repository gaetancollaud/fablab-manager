package net.collaud.fablab.manager.dao;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.data.EventModuleEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>EventModule</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface EventModuleRepository extends JpaRepository<EventModuleEO, Integer> {

    @Override
    @Query("SELECT em "
            + " FROM EventModuleEO em "
            + " LEFT JOIN FETCH em.machineType "
            + " LEFT JOIN FETCH em.prerequisites ")
    List<EventModuleEO> findAll();

    @Query("SELECT em "
            + " FROM EventModuleEO em "
            + " LEFT JOIN FETCH em.machineType "
            + " LEFT JOIN FETCH em.prerequisites "
            + " WHERE em.id=:id")
    Optional<EventModuleEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT em "
            + " FROM EventModuleEO em "
            + " LEFT JOIN FETCH em.machineType "
            + " LEFT JOIN FETCH em.prerequisites "
            + " WHERE UPPER(em.name)=UPPER(:name) ")
    EventModuleEO getId(@Param("name") String name);
}
