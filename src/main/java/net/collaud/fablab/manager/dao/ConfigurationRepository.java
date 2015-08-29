package net.collaud.fablab.manager.dao;

import net.collaud.fablab.manager.data.ConfigurationEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Transactional
public interface ConfigurationRepository extends JpaRepository<ConfigurationEO, Integer>{
    @Query("SELECT c "
            + " FROM ConfigurationEO c "
            + " WHERE c.key = :key")
    ConfigurationEO findByKey(@Param("key") String key);
}
