package net.collaud.fablab.manager.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.audit.Audit;
import net.collaud.fablab.manager.data.ReservationEO;
import net.collaud.fablab.manager.data.type.AuditAction;
import net.collaud.fablab.manager.data.type.AuditObject;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ReservationService extends ReadWriteService<ReservationEO>{

	List<ReservationEO> findReservations(Date dateStart, Date dateEnd);

	@Override
	@Audit(object = AuditObject.RESERVATION, action = AuditAction.DELETE)
	public void remove(Long id);

	@Override
	@Audit(object = AuditObject.RESERVATION, action = AuditAction.SAVE)
	public ReservationEO save(ReservationEO entity);
	
}
