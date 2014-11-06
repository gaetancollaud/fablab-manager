package net.collaud.fablab.api.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gaetan
 */
@Repository
public class ReservationDAOImpl extends AbstractDAO<ReservationEO> implements ReservationDAO {

	public ReservationDAOImpl() {
		super(ReservationEO.class);
	}


	@Override
	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) {
		if (dateStart != null && dateEnd != null && dateStart.after(dateEnd)) {
			return new ArrayList<>();
		}
		
		Query query = createQuery(ReservationEO.SELECT_BY_TIME)
				.setParameter(ReservationEO.PARAM_DATE_START, dateStart)
				.setParameter(ReservationEO.PARAM_DATE_END, dateEnd);
		
		//FIXME take machinesIds in consideration
		
		return query.list();
	}

	@Override
	public ReservationEO save(ReservationEO reservation) throws FablabException {
		return super.saveEntity(reservation);
	}

	@Override
	public void remove(Integer reservationId) throws FablabException {
		super.removeEntityById(reservationId);
	}

}
