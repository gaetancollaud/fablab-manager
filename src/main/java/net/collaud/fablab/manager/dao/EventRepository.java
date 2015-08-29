package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.EventEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Event</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface EventRepository extends JpaRepository<EventEO, Integer> {

    @Query("SELECT e "
            + " FROM EventEO e  "
            + " LEFT JOIN FETCH e.supervisor  "
            + " LEFT JOIN FETCH e.eventType "
            + " LEFT JOIN FETCH e.modules "
            + " LEFT JOIN FETCH e.participants  "
            + " LEFT JOIN FETCH e.organizers  ")
    @Override
    List<EventEO> findAll();

    @Query("SELECT e "
            + " FROM EventEO e "
            + " LEFT JOIN FETCH e.supervisor "
            + " LEFT JOIN FETCH e.supervisor  "
            + " LEFT JOIN FETCH e.eventType "
            + " LEFT JOIN FETCH e.modules "
            + " LEFT JOIN FETCH e.eventType "
            + " LEFT JOIN FETCH e.participants  "
            + " LEFT JOIN FETCH e.organizers  "
            + " WHERE e.id=:id")
    Optional<EventEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT e "
            + " FROM EventEO e "
            + " LEFT JOIN FETCH e.supervisor "
            + " LEFT JOIN FETCH e.supervisor  "
            + " LEFT JOIN FETCH e.eventType "
            + " LEFT JOIN FETCH e.eventType "
            + " LEFT JOIN FETCH e.modules "
            + " LEFT JOIN FETCH e.participants  "
            + " LEFT JOIN FETCH e.organizers  "
            + " WHERE UPPER(e.title) = UPPER(:title)")
    EventEO getId(@Param("title") String title);
}
