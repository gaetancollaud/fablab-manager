package net.collaud.fablab.api.rest.v1.helper;

import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.rest.v1.data.ReservationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gaétan
 */
@Component
public class ReservationTOHelper extends AbstractTOHelper<ReservationTO, ReservationEO> {

	@Override
	public ReservationEO fromTO(ReservationTO to) {
		ReservationEO eo = new ReservationEO();
		eo.setReservationId(to.getReservationId());
		eo.setDateStart(to.getDateStart());
		eo.setDateEnd(to.getDateEnd());
		eo.setMachine(new MachineEO(to.getMachineId()));
		eo.setUser(new UserEO(to.getMachineId()));
		return eo;
	}

	@Override
	public ReservationTO fromEO(ReservationEO eo) {
		ReservationTO to = new ReservationTO();
		to.setReservationId(eo.getReservationId());
		to.setDateStart(eo.getDateStart());
		to.setDateEnd(eo.getDateEnd());
		to.setUserId(eo.getUser().getId());
		to.setMachineId(eo.getMachine().getId());
		return to;
	}

}
