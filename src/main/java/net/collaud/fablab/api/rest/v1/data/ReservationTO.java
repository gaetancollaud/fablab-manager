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
	
	private Integer userId;
	
	private Integer machineId;

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
