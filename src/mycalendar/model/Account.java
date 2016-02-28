package mycalendar.model;

import java.util.ArrayList;

public class Account {

	private int accountID;
	private String accountName;
	private String accountEmail;
	private String accountPassword;
	
	private ArrayList<Event> associatedEvents = new ArrayList<>();
	
	public Account() {}

	public Account(Integer accountID, String accountName, String accountEmail, String accountPassword, ArrayList<Event> events) {
		super();
		this.accountID = accountID;
		this.accountName = accountName;
		this.accountEmail = accountEmail;
		this.accountPassword = accountPassword;
		this.associatedEvents = events;
	}

	public ArrayList<Event> getAssociatedEvents() {
		return associatedEvents;
	}

	public void setAssociatedEvents(ArrayList<Event> associatedEvents) {
		this.associatedEvents = associatedEvents;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	};
	
}
