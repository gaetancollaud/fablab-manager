package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.exceptions.FablabWrongParameterException;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.ReservationService;
import net.collaud.fablab.api.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaétan
 */
@Service
@Transactional
@Secured({RolesHelper.ROLE_ADMIN})
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOG = Logger.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationDAO reservationDao;
	
	@Autowired
	private SecurityService securityService;

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_RESERVATION, RolesHelper.ROLE_USE_RESERVATION})
	public ReservationEO save(ReservationEO reservation) throws FablabException {
		LOG.debug("Save "+reservation);
		reservation.setUser(new UserEO(securityService.getCurrentUserId()));
		return reservationDao.save(reservation);
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_RESERVATION, RolesHelper.ROLE_USE_RESERVATION})
	public void remove(Integer reservationId) throws FablabException {
		LOG.debug("Remove with id"+reservationId);
		reservationDao.remove(reservationId);
	}

	@Override
	@Secured({RolesHelper.ROLE_VIEW_RESERVATION})
	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds) throws FablabException {
		LOG.debug("find reservation from "+dateStart+" to "+dateEnd+" of machines "+machineIds);
		return reservationDao.findReservations(dateStart, dateEnd, machineIds);
	}

}
