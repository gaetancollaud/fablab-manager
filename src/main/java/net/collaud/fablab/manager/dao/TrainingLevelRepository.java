package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.TrainingLevelEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *This is the DAO interface for a <tt>TrainingLevel</tt>.
 * @author Fabien Vuilleumier
 */
public interface TrainingLevelRepository extends JpaRepository<TrainingLevelEO, Integer>{

}
