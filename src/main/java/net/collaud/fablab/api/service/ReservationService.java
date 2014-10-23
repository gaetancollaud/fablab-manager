package net.collaud.fablab.api.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;

/**
 *
 * @author gaetan
 */
public interface ReservationService {

	ReservationEO save(ReservationEO reservation) throws FablabException;

	void remove(ReservationEO reservation) throws FablabException;

	List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) throws FablabException;

	
}
