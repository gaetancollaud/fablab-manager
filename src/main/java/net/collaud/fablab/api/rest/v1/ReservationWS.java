package net.collaud.fablab.api.rest.v1;

import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.criteria.ReservationSearchCriteria;
import net.collaud.fablab.api.rest.v1.data.ReservationTO;
import net.collaud.fablab.api.rest.v1.helper.ReservationTOHelper;
import net.collaud.fablab.api.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaétan
 */
@RestController()
@RequestMapping("/v1/reservation")
public class ReservationWS {

	private static final Logger LOG = LogManager.getLogger(ReservationWS.class);

	@Autowired
	private ReservationTOHelper reservationHelper;

	@Autowired
	private ReservationService reservationService;

	@RequestMapping(value = "search", method = RequestMethod.POST)
//	@Secured({RolesHelper.ROLE_VIEW_RESERVATION})
	public List<ReservationTO> list(@RequestBody ReservationSearchCriteria criteria) throws FablabException {
		LOG.debug("Search reservation " + criteria);
		List<ReservationEO> list = reservationService.findReservations(
				criteria.getDateFrom(),
				criteria.getDateTo(),
				criteria.getMachineIds());
		return reservationHelper.fromEOList(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody ReservationTO to) throws FablabException {
		ReservationEO eo = reservationHelper.fromTO(to);
		eo.setReservationId(0);
		LOG.debug("create reservation " + eo);
		reservationService.save(eo);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void edit(@PathVariable Integer id, @RequestBody ReservationTO to) throws FablabException {
		LOG.debug("edit reservation " + to);
		ReservationEO eo = reservationHelper.fromTO(to);
		eo.setReservationId(id);
		reservationService.save(eo);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable Integer id) throws FablabException {
		LOG.debug("delete reservation with id " + id);
		reservationService.remove(id);
	}

}
