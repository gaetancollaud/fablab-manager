package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.audit.Audit;
import net.collaud.fablab.manager.data.AssetEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.service.global.ReadService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface AssetService extends ReadService<AssetEO>{

	@Audit(object = AuditObject.ASSET, action = AuditAction.INSERT)
	AssetEO upload(MultipartFile file);

	@Audit(object = AuditObject.ASSET, action = AuditAction.DELETE)
	void remove(Long id);

}
