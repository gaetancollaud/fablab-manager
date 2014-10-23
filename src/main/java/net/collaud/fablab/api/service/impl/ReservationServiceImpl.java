package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.exceptions.FablabWrongParameterException;
import net.collaud.fablab.api.service.ReservationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaétan
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOG = Logger.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationDAO reservationDao;

	@Override
	public ReservationEO save(ReservationEO reservation) throws FablabException {
		return reservationDao.save(reservation);
	}

	@Override
	public void remove(ReservationEO reservation) throws FablabException {
		reservationDao.remove(reservation);
	}

	@Override
	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) throws FablabException {
		if(dateStart==null || dateEnd==null){
			throw new FablabWrongParameterException("DateStart and dateEnd cannot be null");
		}
		if(dateStart.after(dateEnd)){
			throw  new FablabWrongParameterException("DateStart must be before dateEnd");
		}
		return reservationDao.findReservations(dateStart, dateEnd, machineIds);
	}

}
