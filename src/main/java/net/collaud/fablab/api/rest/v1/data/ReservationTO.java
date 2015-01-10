package net.collaud.fablab.api.rest.v1.data;

import java.util.Date;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
public class ReservationTO extends AbstractTO<ReservationEO, ReservationTO> {

	private Integer reservationId;

	private Date dateStart;

	private Date dateEnd;

	private Integer userId;

	private Integer machineId;

	public ReservationTO() {
	}

	public ReservationTO(Integer reservationId, Date dateStart, Date dateEnd, Integer userId, Integer machineId) {
		this.reservationId = reservationId;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.userId = userId;
		this.machineId = machineId;
	}

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
		setReservationId(eo.getReservationId());
		setDateStart(eo.getDateStart());
		setDateEnd(eo.getDateEnd());
		setUserId(eo.getUser().getUserId());
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

}
