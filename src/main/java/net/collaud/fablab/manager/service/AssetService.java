package net.collaud.fablab.manager.service;

import java.util.Optional;
import net.collaud.fablab.manager.data.AssetEO;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface AssetService {

	Optional<AssetEO> getById(Integer id);

	AssetEO upload(String name, MultipartFile file);
	
	void remove(Integer id);

}
