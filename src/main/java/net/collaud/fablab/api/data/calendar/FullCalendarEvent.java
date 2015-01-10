package net.collaud.fablab.api.data.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.data.UserEO;

/**
 * This class is mapped from event object of the fullcalendar library
 * (http://arshaw.com/fullcalendar/docs/)
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class FullCalendarEvent {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private int id;
	private String title;
	private boolean allDay;
	private String start;
	private String end;
	private String url;
	private String className;
	private boolean editable;
	private String description;

	//FIXME sortir texte
	public static FullCalendarEvent fromReservation(ReservationEO reservation, UserEO currentUser) {
		String username = reservation.getUser().equals(currentUser) ? "you" : reservation.getUser().getFirstLastName();
		String title = reservation.getMachine().getName()+" by " + username;
		String url = "";
		boolean editable = reservation.getUser().equals(currentUser);
		String classname = "reservation_"+reservation.getMachine().getMachineType().getTechnicalname().toLowerCase();
		if(editable){
			classname+= " editable";
		}
		String description = "description";
		return new FullCalendarEvent(reservation.getReservationId(), title, false,
				sdf.format(reservation.getDateStart()), sdf.format(reservation.getDateEnd()), url,
				classname, editable, description);
	}

	public static List<FullCalendarEvent> fromReservations(List<ReservationEO> list, UserEO currentUser) {
		List<FullCalendarEvent> ret = new ArrayList<>(list.size());
		for (ReservationEO res : list) {
			ret.add(fromReservation(res, currentUser));
		}
		return ret;
	}

	public FullCalendarEvent() {
	}

	public FullCalendarEvent(int id, String title, boolean allDay, String start, String end, String url,
			String className, boolean editable, String description) {
		this.id = id;
		this.title = title;
		this.allDay = allDay;
		this.start = start;
		this.end = end;
		this.url = url;
		this.className = className;
		this.editable = editable;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
