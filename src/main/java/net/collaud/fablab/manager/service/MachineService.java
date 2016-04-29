package net.collaud.fablab.manager.service;

import net.collaud.fablab.manager.audit.Audit;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.service.global.ReadWriteService;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineService extends ReadWriteService<MachineEO> {
	@Override
	@Audit(object = AuditObject.MACHINE, action = AuditAction.SAVE)
	MachineEO save(MachineEO entity);

	@Override
	@Audit(object = AuditObject.MACHINE, action = AuditAction.DELETE)
	void remove(Long id);
}
