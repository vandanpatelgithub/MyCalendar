package mycalendar.model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	private int eventID;
	private String eventName;
	private String eventCreater;
	private String eventTime;
	private String location;
	

	public Event(){}

	public Event(int eventID, String eventName, String eventCreater, String eventTime, String location) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventCreater = eventCreater;
		this.eventTime = eventTime;
		this.location = location;
		
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
}
