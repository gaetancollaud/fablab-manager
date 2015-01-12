package net.collaud.fablab.api.rest.v1.data;

import java.util.Date;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
public class ReservationSimpleTO extends AbstractTO<ReservationEO, ReservationSimpleTO> {

	private Integer reservationId;

	private Date dateStart;

	private Date dateEnd;

	private UserSimpleTO user;

	private Integer machineId;

	public ReservationSimpleTO() {
	}

	@Override
	public ReservationEO convertToEO() {
		ReservationEO eo = new ReservationEO();
		eo.setReservationId(getReservationId());
		eo.setDateStart(getDateStart());
		eo.setDateEnd(getDateEnd());
		eo.setMachine(new MachineEO(getMachineId()));
		eo.setUser(new UserEO(getMachineId()));
		return eo;
	}

	@Override
	public ReservationSimpleTO fromEO(ReservationEO eo) {
		setReservationId(eo.getReservationId());
		setDateStart(eo.getDateStart());
		setDateEnd(eo.getDateEnd());
		setUser(new UserSimpleTO().fromEO(eo.getUser()));
		setMachineId(eo.getMachine().getMachineId());
		return this;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public UserSimpleTO getUser() {
		return user;
	}

	public void setUser(UserSimpleTO user) {
		this.user = user;
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

}
