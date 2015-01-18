package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.ReservationService;
import net.collaud.fablab.api.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Secured({RolesHelper.ROLE_ADMIN})
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOG = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationDAO reservationDao;
	
	@Autowired
	private SecurityService securityService;

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_RESERVATION, RolesHelper.ROLE_USE_RESERVATION})
	public ReservationEO save(ReservationEO reservation) {
		LOG.debug("Save "+reservation);
		reservation.setUser(new UserEO(securityService.getCurrentUserId()));
		return reservationDao.save(reservation);
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_RESERVATION, RolesHelper.ROLE_USE_RESERVATION})
	public void remove(Integer reservationId) {
		LOG.debug("Remove with id"+reservationId);
		reservationDao.delete(reservationId);
	}

	@Override
	@Secured({RolesHelper.ROLE_VIEW_RESERVATION})
	public List<ReservationEO> findReservations(Date dateStart, Date dateEnd, List<Integer> machineIds){
		LOG.debug("find reservation from "+dateStart+" to "+dateEnd+" of machines "+machineIds);
		return reservationDao.findReservations(dateStart, dateEnd);
	}

	@Override
	public List<ReservationEO> findAll() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Optional<ReservationEO> getById(Integer id) {
		return Optional.ofNullable(reservationDao.findOne(id));
	}

}
