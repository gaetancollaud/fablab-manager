package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.data.AssetEO;
import net.collaud.fablab.manager.service.global.ReadService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface AssetService extends ReadService<AssetEO>{

	AssetEO upload(MultipartFile file);
	
	void remove(Integer id);

}
