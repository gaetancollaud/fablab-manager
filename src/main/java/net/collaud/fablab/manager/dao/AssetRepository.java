package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.AssetEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Transactional
public interface AssetRepository extends JpaRepository<AssetEO, Integer>{

	@Override
	@Query("SELECT DISTINCT a "
			+ " FROM AssetEO a "
			+ " LEFT JOIN FETCH a.owner ")
	List<AssetEO> findAll();
	
	@Query("SELECT DISTINCT a "
			+ " FROM AssetEO a "
			+ " LEFT JOIN FETCH a.owner "
			+ " WHERE a.id=:id")
	AssetEO findOne(@Param("id") Integer id);
	
	
}
