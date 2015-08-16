package net.collaud.fablab.manager.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.manager.data.ReservationEO;
import net.collaud.fablab.manager.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ReservationService extends ReadWriteService<ReservationEO>{

	List<ReservationEO> findReservations(Date dateStart, Date dateEnd);
	
}
