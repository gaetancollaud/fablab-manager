package net.collaud.fablab.api.rest.v1.criteria;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Gaétan
 */
public class ReservationSearchCriteria {
	private Date dateFrom;
	private Date dateTo;
	private List<Integer> machineIds;

	public ReservationSearchCriteria() {
	}

	public ReservationSearchCriteria(Date dateFrom, Date dateTo, List<Integer> machineIds) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.machineIds = machineIds;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<Integer> getMachineIds() {
		return machineIds;
	}

	public void setMachineIds(List<Integer> machineIds) {
		this.machineIds = machineIds;
	}

	@Override
	public String toString() {
		return "dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", machineIds=" + machineIds;
	}
}
