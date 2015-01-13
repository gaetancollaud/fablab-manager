package net.collaud.fablab.api.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.api.dao.ReservationDAO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.base.ReadRestWebservice;
import net.collaud.fablab.api.rest.v1.criteria.ReservationSearchCriteria;
import net.collaud.fablab.api.rest.v1.data.AbstractTO;
import net.collaud.fablab.api.rest.v1.data.ReservationSimpleTO;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ReservationWS extends ReadRestWebservice<ReservationEO, ReservationDAO>{

	private static final Logger LOG = LoggerFactory.getLogger(ReservationWS.class);

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationDAO reservationDao;
	
	@PostConstruct
	private void postConstruct(){
		super.setRepository(reservationDao);
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	@Secured({RolesHelper.ROLE_VIEW_RESERVATION})
	public List<ReservationSimpleTO> list(@RequestBody ReservationSearchCriteria criteria) throws FablabException {
		LOG.debug("Search reservation " + criteria);
		List<ReservationEO> list = reservationService.findReservations(
				criteria.getDateFrom(),
				criteria.getDateTo(),
				criteria.getMachineIds());
		return AbstractTO.fromEOList(list, ReservationEO.class, ReservationSimpleTO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Secured({RolesHelper.ROLE_USE_RESERVATION})
	public void create(@RequestBody ReservationSimpleTO to) throws FablabException {
		ReservationEO eo = to.convertToEO();
		eo.setReservationId(0);
		LOG.debug("create reservation " + eo);
		reservationService.save(eo);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@Secured({RolesHelper.ROLE_USE_RESERVATION})
	public void edit(@PathVariable Integer id, @RequestBody ReservationSimpleTO to) throws FablabException {
		LOG.debug("edit reservation " + to);
		ReservationEO eo = to.convertToEO();
		eo.setReservationId(id);
		reservationService.save(eo);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@Secured({RolesHelper.ROLE_USE_RESERVATION})
	public void remove(@PathVariable Integer id) throws FablabException {
		LOG.debug("delete reservation with id " + id);
		reservationService.remove(id);
	}

}
