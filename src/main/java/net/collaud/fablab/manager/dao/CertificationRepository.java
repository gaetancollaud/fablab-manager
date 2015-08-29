package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.CertificationEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Certification</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface CertificationRepository extends JpaRepository<CertificationEO, Integer> {

    @Query("SELECT c "
            + " FROM CertificationEO c  "
            + " LEFT JOIN FETCH c.training t "
            + " LEFT JOIN FETCH t.machineType "
            + " LEFT JOIN FETCH c.users ")
    @Override
    List<CertificationEO> findAll();

    @Query("SELECT c "
            + " FROM CertificationEO c "
            + " LEFT JOIN FETCH c.training t "
            + " LEFT JOIN FETCH t.machineType "
            + " LEFT JOIN FETCH c.users "
            + " WHERE c.id=:id")
    Optional<CertificationEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT c "
            + " FROM CertificationEO c "
            + " LEFT JOIN FETCH c.training "
            + " LEFT JOIN FETCH c.users "
            + " WHERE UPPER(c.name) = UPPER(:name)")
    CertificationEO getId(@Param("name") String name);

    @Query("SELECT c "
            + " FROM CertificationEO c "
            + " LEFT JOIN FETCH c.training t "
            + " LEFT JOIN FETCH t.machineType "
            + " LEFT JOIN FETCH c.users u "
            + " WHERE u.id = :id ")
    List<CertificationEO> getCertificationsByUserId(@Param("id") Integer userId);

    @Query("SELECT c "
            + " FROM CertificationEO c "
            + " WHERE c.training.id = :trainingId")
    CertificationEO getCertification(@Param("trainingId") Integer trainingId);

}
