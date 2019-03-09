package net.collaud.fablab.manager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.dao.ReservationRepository;
import net.collaud.fablab.manager.data.ReservationEO;
import net.collaud.fablab.manager.data.UserEO;
import net.collaud.fablab.manager.security.Roles;
import net.collaud.fablab.manager.service.ReservationService;
import net.collaud.fablab.manager.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Secured({Roles.ADMIN})
@Slf4j
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private SecurityService securityService;

	@Override
	@Secured({Roles.RESERVATION_MANAGE, Roles.RESERVATION_USE})
	public ReservationEO save(ReservationEO reservation) {
		LOG.debug("Save "+reservation);
		reservation.setUser(new UserEO(securityService.getCurrentUserId()));
		return reservationRepository.save(reservation);
	}

	@Override
	@Secured({Roles.RESERVATION_MANAGE, Roles.RESERVATION_USE})
	public void remove(Long reservationId) {
		LOG.debug("Remove with id"+reservationId);
		reservationRepository.delete(reservationId);
	}

	@Override
	@Secured({Roles.RESERVATION_VIEW})
	public List<ReservationEO> findReservations(Date dateFrom, Date dateTo){
		LOG.debug("find reservation from "+dateFrom+" to "+dateTo);
		return reservationRepository.findReservations(dateFrom, dateTo);
	}

	@Override
	public List<ReservationEO> findAll() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Optional<ReservationEO> getById(Long id) {
		return Optional.ofNullable(reservationRepository.findOne(id));
	}

}
