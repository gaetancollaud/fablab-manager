package net.collaud.fablab.manager.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.RevisionEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import net.collaud.fablab.manager.data.UserEO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Revision</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface RevisionRepository extends JpaRepository<RevisionEO, Integer> {

    @Query("SELECT r "
            + " FROM RevisionEO r "
            + " LEFT JOIN FETCH r.machine "
            + " WHERE r.active = true")
    @Override
    List<RevisionEO> findAll();

    @Query("SELECT r "
            + " FROM RevisionEO r "
            + " LEFT JOIN FETCH r.machine "
            + " WHERE r.id=:id AND r.active = true")
    Optional<RevisionEO> findOneDetails(@Param("id") Integer id);

    @Query(" SELECT r "
            + " FROM RevisionEO r "
            + " LEFT JOIN FETCH r.machine m "
            + " WHERE m.id = :id  AND r.active = true")
    List<RevisionEO> listByMachine(@Param("id") Integer id);

    @Query(" SELECT r "
            + " FROM RevisionEO r "
            + " LEFT JOIN FETCH r.machine "
            + " LEFT JOIN FETCH r.user "
            + " WHERE r.revisionDate>=:dateAfter AND r.revisionDate <=:dateBefore")
    public List<RevisionEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);

    @Query("SELECT r "
            + " FROM RevisionEO r "
            + " LEFT JOIN FETCH r.machine "
            + " LEFT JOIN FETCH r.user "
            + " WHERE r.user.id = :id")
    public List<RevisionEO> findAllWithActive(@Param("id") UserEO user);

}
