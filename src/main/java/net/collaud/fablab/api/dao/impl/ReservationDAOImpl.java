package net.collaud.fablab.api.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.exceptions.FablabWrongParameterException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Repository
@Transactional
public class ReservationDAOImpl extends AbstractDAO<ReservationEO> implements ReservationDAO {

	public ReservationDAOImpl() {
		super(ReservationEO.class);
	}

	@Override
	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) throws FablabWrongParameterException {
		if (dateStart == null || dateEnd == null) {
			throw new FablabWrongParameterException("DateStart and dateEnd cannot be null");
		}
		if (dateStart.after(dateEnd)) {
			throw new FablabWrongParameterException("DateStart must be before dateEnd");
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

	@Override
	public void removeAllReservations() {
		createQuery("DELETE FROM ReservationEO").executeUpdate();
	}

}
