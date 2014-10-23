package net.collaud.fablab.api.rest.v1;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.data.ReservationTO;
import net.collaud.fablab.api.rest.v1.helper.ReservationTOHelper;
import net.collaud.fablab.api.security.RolesHelper;
import net.collaud.fablab.api.service.ReservationService;
import net.collaud.fablab.api.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaétan
 */
@RestController()
@RequestMapping("/api/v1/reservation")
@PreAuthorize(RolesHelper.HAS_ROLE_ADMIN)
public class ReservationWS {

	private static final Logger LOG = Logger.getLogger(ReservationWS.class);

	@Autowired
	private ReservationTOHelper reservationHelper;

	@Autowired
	private ReservationService reservationService;

	@RequestMapping()
	public List<ReservationTO> list(){
		try {
			Date start = new Date(0, 0, 1);
			Date end = new Date(2020, 0, 1);
			List<ReservationEO> list = reservationService.findReservations(start, end, null);
			return reservationHelper.fromEOList(list);
		} catch (Exception ex) {
			LOG.error("Cannot list reservation", ex);
		}
		return null;
	}

}
