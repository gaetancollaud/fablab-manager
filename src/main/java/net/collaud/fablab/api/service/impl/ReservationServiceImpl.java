package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.dao.ReservationRepository;
import net.collaud.fablab.api.dao.specifications.ReservationSpecifications;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.ReservationService;
import net.collaud.fablab.api.service.SecurityService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
@Secured({RolesHelper.ROLE_ADMIN})
@Slf4j
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationDao;
	
	@Autowired
	private SecurityService securityService;

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_RESERVATION, RolesHelper.ROLE_USE_RESERVATION})
	public ReservationEO save(ReservationEO reservation) {
		log.debug("Save "+reservation);
		reservation.setUser(new UserEO(securityService.getCurrentUserId()));
		return reservationDao.save(reservation);
	}

	@Override
	@Secured({RolesHelper.ROLE_MANAGE_RESERVATION, RolesHelper.ROLE_USE_RESERVATION})
	public void remove(Integer reservationId) {
		log.debug("Remove with id"+reservationId);
		reservationDao.delete(reservationId);
	}

	@Override
	@Secured({RolesHelper.ROLE_VIEW_RESERVATION})
	public List<ReservationEO> findReservations(Date dateFrom, Date dateTo, List<Integer> machineIds){
		log.debug("find reservation from "+dateFrom+" to "+dateTo+" of machines "+machineIds);
		Specifications spec = Specifications.where(ReservationSpecifications.joins());
		if(dateFrom!=null){
			spec = spec.and(ReservationSpecifications.from(dateFrom));
		}
		if(dateTo!=null){
			spec = spec.and(ReservationSpecifications.to(dateTo));
		}
		if(machineIds!=null && !machineIds.isEmpty()){
			spec = spec.and(ReservationSpecifications.machines(machineIds));
		}
		return reservationDao.findAll(spec);
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
