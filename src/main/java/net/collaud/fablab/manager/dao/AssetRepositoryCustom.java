package net.collaud.fablab.manager.dao;

import java.util.List;
import net.collaud.fablab.manager.data.AssetEO;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Transactional
public interface AssetRepositoryCustom {

	AssetEO findOneWithoutContent(Integer id);

	AssetEO findOneWithContent(Integer id);
	
	List<AssetEO> findAll();
	
	List<AssetEO> findAllForOwner(Integer userId);

}
