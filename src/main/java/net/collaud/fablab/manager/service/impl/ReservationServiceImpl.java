package net.collaud.fablab.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud
 * <gaetancollaud@gmail.com>
 */
@Service
@Transactional
@Secured({Roles.ADMIN})
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationDao;

    @Autowired
    private SecurityService securityService;

    @Override
    @Secured({Roles.RESERVATION_MANAGE, Roles.RESERVATION_USE})
    public ReservationEO save(ReservationEO reservation) {
        log.debug("Save " + reservation);
        reservation.setUser(new UserEO(securityService.getCurrentUserId()));
        return reservationDao.save(reservation);
    }

    @Override
    @Secured({Roles.RESERVATION_MANAGE, Roles.RESERVATION_USE})
    public void remove(Integer id) {
        ReservationEO current = reservationDao.findOne(id);
        current.setActive(false);
        reservationDao.saveAndFlush(current);
    }

    @Override
    @Secured({Roles.RESERVATION_VIEW})
    public List<ReservationEO> findReservations(Date from, Date to) {
        log.debug("find reservation from " + from + " to " + to);
        return reservationDao.findReservations(from, to);
    }

    @Override
    public List<ReservationEO> findAll() {
        return new ArrayList(new HashSet(reservationDao.findAll()));
    }

    @Override
    public Optional<ReservationEO> getById(Integer id) {
        return Optional.ofNullable(reservationDao.findOne(id));
    }

}
