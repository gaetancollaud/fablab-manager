package net.collaud.fablab.api.rest.v1.data;

import java.util.Date;

/**
 *
 * @author Gaétan
 */
public class ReservationTO extends AbstractTO{
	
	private Integer reservationId;
	
	private Date dateStart;
	
	private Date dateEnd;
	
	private UserTO user;
	
	private MachineTO machine;

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

	public UserTO getUser() {
		return user;
	}

	public void setUser(UserTO user) {
		this.user = user;
	}

	public MachineTO getMachine() {
		return machine;
	}

	public void setMachine(MachineTO machine) {
		this.machine = machine;
	}
	
}
