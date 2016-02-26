package mycalendar.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	private int eventID;
	private String eventName;
	private String eventCreater;
	private Date eventTime;
	
	private ArrayList<Account> eventAttendees = new ArrayList<>();
	
	public Event(){}

	public Event(int eventID, String eventName, String eventCreater, Date eventTime,
			ArrayList<Account> eventAttendees) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventCreater = eventCreater;
		this.eventTime = eventTime;
		this.eventAttendees = eventAttendees;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventCreater() {
		return eventCreater;
	}

	public void setEventCreater(String eventCreater) {
		this.eventCreater = eventCreater;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public ArrayList<Account> getEventAttendees() {
		return eventAttendees;
	}

	public void setEventAttendees(ArrayList<Account> eventAttendees) {
		this.eventAttendees = eventAttendees;
	};
	
	
	
}
