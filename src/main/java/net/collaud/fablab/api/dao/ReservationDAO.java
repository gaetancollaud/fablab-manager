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

	public ReservationEO save(ReservationEO current) throws FablabException;

	public void remove(ReservationEO current) throws FablabException;

	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) throws FablabException;

}
