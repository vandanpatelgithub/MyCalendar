package mycalendar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mycalendar.model.Event;

public class EventServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private volatile int EVENT_ID_SEQUENCE = 4;
	
	private ArrayList<Event> eventsDatabase = new ArrayList<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
				
		Event eventOne = new Event(1, "Cowboy Hat Sale", "Hank", new Date());
		Event eventTwo = new Event(2, "Whiskey Tasting", "Bobby", new Date());
		Event eventThree = new Event(3, "Pure Country Music Show", "Dale R. Mercer", new Date());
		
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
				createEvent(request,response);
				break;
			
			case "yourEvents":
				yourEvents(request,response);
				break;
			
			case "list":
			default:
				showListings(request, response);
				break;
		}
	}

	private void yourEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username") != null)
		{
			request.setAttribute("loggedIn", true);
		}
		request.getRequestDispatcher("/WEB-INF/yourevents.jsp").forward(request, response);
	}

	private void createEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
