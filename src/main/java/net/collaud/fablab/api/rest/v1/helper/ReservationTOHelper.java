package net.collaud.fablab.api.rest.v1.helper;

import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.rest.v1.data.ReservationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaétan
 */
@Component
public class ReservationTOHelper extends AbstractTOHelper<ReservationTO, ReservationEO> {

	@Autowired
	private UserTOHelper userTOHelper;
	
	@Autowired
	private MachineTOHelper machineTOHelper;

	@Override
	public ReservationEO fromTO(ReservationTO to) {
		ReservationEO eo = new ReservationEO();

		//return eo;
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public ReservationTO fromEO(ReservationEO eo) {
		ReservationTO to = new ReservationTO();
		to.setReservationId(eo.getReservationId());
		to.setDateStart(eo.getDateStart());
		to.setDateEnd(eo.getDateEnd());
		to.setUser(userTOHelper.fromEO(eo.getUser()));
		to.setMachine(machineTOHelper.fromEO(eo.getMachine()));
		return to;
	}

}
