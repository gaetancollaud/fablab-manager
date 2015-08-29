package net.collaud.fablab.manager.dao;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.manager.data.EventPersonEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>EventPerson</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface EventPersonRepository extends JpaRepository<EventPersonEO, Integer> {

    @Override
    @Query("SELECT ep "
            + " FROM EventPersonEO ep "
            + " LEFT JOIN FETCH ep.acquiredModules ")
    List<EventPersonEO> findAll();

    @Query("SELECT ep "
            + " FROM EventPersonEO ep "
            + " LEFT JOIN FETCH ep.acquiredModules "
            + " WHERE ep.id=:id")
    Optional<EventPersonEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT ep "
            + " FROM EventPersonEO ep "
            + " LEFT JOIN FETCH ep.acquiredModules "
            + " WHERE UPPER(ep.email)=UPPER(:email) ")
    EventPersonEO getId(@Param("email") String email);
}
