package net.collaud.fablab.api.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.criteria.ReservationSearchCriteria;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/reservation")
@Secured({RolesHelper.ROLE_ADMIN})
@Slf4j
public class ReservationWS extends ReadWriteRestWebservice<ReservationEO, ReservationService>{

	@Autowired
	private ReservationService reservationService;
	
	@PostConstruct
	private void postConstruct(){
		super.setService(reservationService);
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	@Secured({RolesHelper.ROLE_VIEW_RESERVATION})
	public List<ReservationEO> list(@RequestBody ReservationSearchCriteria criteria) {
		log.debug("Search reservation " + criteria);
		List<ReservationEO> list = reservationService.findReservations(
				criteria.getDateFrom(),
				criteria.getDateTo(),
				criteria.getMachineIds());
		return list;
	}

}
