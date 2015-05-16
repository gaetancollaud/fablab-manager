package net.collaud.fablab.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.dao.ReservationRepository;
import net.collaud.fablab.api.dao.specifications.ReservationSpecifications;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.ReservationService;
import net.collaud.fablab.api.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
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
		log.debug("Save "+reservation);
		reservation.setUser(new UserEO(securityService.getCurrentUserId()));
		return reservationRepository.save(reservation);
	}

	@Override
	@Secured({Roles.RESERVATION_MANAGE, Roles.RESERVATION_USE})
	public void remove(Integer reservationId) {
		log.debug("Remove with id"+reservationId);
		reservationRepository.delete(reservationId);
	}

	@Override
	@Secured({Roles.RESERVATION_VIEW})
	public List<ReservationEO> findReservations(Date dateFrom, Date dateTo){
		log.debug("find reservation from "+dateFrom+" to "+dateTo);
		return reservationRepository.findReservations(dateFrom, dateTo);
	}

	@Override
	public List<ReservationEO> findAll() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Optional<ReservationEO> getById(Integer id) {
		return Optional.ofNullable(reservationRepository.findOne(id));
	}

}
