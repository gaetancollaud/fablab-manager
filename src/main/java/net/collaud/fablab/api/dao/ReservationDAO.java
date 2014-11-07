package net.collaud.fablab.api.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;

/**
 *
 * @author gaetan
 */
public interface ReservationDAO {

	ReservationEO save(ReservationEO reservation) throws FablabException;

	void remove(Integer reservationId) throws FablabException;

	List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) throws FablabException;
	
	void removeAllReservations();

}
