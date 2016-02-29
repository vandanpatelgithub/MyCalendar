package mycalendar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mycalendar.model.Account;
import mycalendar.model.Event;
import mycalendar.servlets.*;

public class EventServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private volatile int EVENT_ID_SEQUENCE = 4;
	
	private ArrayList<Event> eventsDatabase = new ArrayList<>();
	
	private ArrayList<Event> associatedEvents = new ArrayList<>();
	
	
	// The problem is this accountsDatabse is coming empty
	Hashtable<String,Account> accountsDatabase = LoginServlet.getAccountsDatabase();
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
				
		Event eventOne = new Event(1, "Cowboy Hat Sale", "Hank", "11:00am","Fullerton, CA");
		Event eventTwo = new Event(2, "Whiskey Tasting", "Bobby", "12:00pm","Los Angeles,CA");
		Event eventThree = new Event(3, "Pure Country Music Show", "Dale R. Mercer", "4:00pm","Long Beach, CA");
		
	
		//Add Dummy Data
		eventsDatabase.add(eventOne);
		eventsDatabase.add(eventTwo);
		eventsDatabase.add(eventThree);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(request.getParameter("logout")!=null){
			session.invalidate();
			response.sendRedirect("login");
			return;
		}
		
		String action = request.getParameter("action");
		
		if(action == null)
			action = "list";
		
		switch(action){
			case "create": 
				showCreateEventForm(request,response);
				break;
				
			case "yourEvents":
				showYourEvents(request,response);
				break;
				
			case "list":
			default:
				showListings(request, response);
				break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		switch(action){
			case "create":
				createEvent(request,response);
				break;
			
		}
	}


	private void createEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		String username = (String) session.getAttribute("username");
		String eventName = request.getParameter("eventName");
		String eventTime = request.getParameter("eventTime");
		String location = request.getParameter("eventLocation");
		
		int id;
		synchronized(this){
			id = this.EVENT_ID_SEQUENCE++;
		}
		
	
		Event newEvent = new Event(id , eventName, username, eventTime, location);
		
		eventsDatabase.add(newEvent);
		
		Account userAccount = accountsDatabase.get(username);
		
		associatedEvents = userAccount.getAssociatedEvents();
		
		associatedEvents.add(newEvent);
		
		response.sendRedirect("events");
		
	}

	private void showYourEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username") != null)
		{
			String username = (String) session.getAttribute("username");
			
			if(accountsDatabase.size() == 0){
				System.out.println("Account Database is empty!");
			}
			
			Account userAccount = accountsDatabase.get(username);
			
			associatedEvents = userAccount.getAssociatedEvents();
			
			request.setAttribute("associatedEvents", this.associatedEvents);
			
			request.setAttribute("loggedIn", true);
		}
		request.getRequestDispatcher("/WEB-INF/yourevents.jsp").forward(request, response);
	}

	private void showCreateEventForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username") != null)
		{
			request.setAttribute("loggedIn", true);
		}
		request.getRequestDispatcher("/WEB-INF/create_event.jsp").forward(request, response);
	}

	private void showListings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("eventsDatabase", eventsDatabase);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username") != null)
		{
			request.setAttribute("loggedIn", true);
		}
		
		request.getRequestDispatcher("/WEB-INF/eventslist.jsp").forward(request, response);
	}

}
