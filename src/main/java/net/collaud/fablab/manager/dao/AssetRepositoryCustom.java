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

	AssetEO findOneWithoutContent(Long id);

	AssetEO findOneWithContent(Long id);
	
	List<AssetEO> findAll();
	
	List<AssetEO> findAllForOwner(Long userId);

}
