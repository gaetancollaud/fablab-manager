package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.TicketStatusEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>TicketStatus</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface TicketStatusRepository extends JpaRepository<TicketStatusEO, Integer> {

    @Query("SELECT t "
            + " FROM TicketStatusEO t "
            + " WHERE UPPER(t.label) = UPPER(:label)")
    TicketStatusEO findByLabel(@Param("label") String label);

}
