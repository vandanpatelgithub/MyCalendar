package mycalendar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import mycalendar.model.Account;
import mycalendar.model.Event;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private volatile int ACCOUNT_ID_SEQUENCE = 4;
	
	private static Hashtable<String,Account> accountsDatabase = new Hashtable<>();
	
	private ArrayList<Event> associatedEvents = new ArrayList<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		Event eventOne = new Event(1, "Cowboy Hat Sale", "Hank", "11:00am","Fullerton, CA");
		Event eventTwo = new Event(2, "Whiskey Tasting", "Bobby", "12:00pm","Los Angeles,CA");
		Event eventThree = new Event(3, "Pure Country Music Show", "Dale R. Mercer", "4:00pm","Long Beach, CA");
		
		associatedEvents.add(eventOne);
		associatedEvents.add(eventTwo);
		associatedEvents.add(eventThree);
		
		Account accountOne = new Account(1, "andrew", "andrew@gmail.com", "test12",associatedEvents);
		Account accountTwo = new Account(2, "bob", "bobson@gmail.com", "test12",associatedEvents);
		Account accountThree = new Account(3, "chang", "chang@gmail.com", "test12", associatedEvents);
		
		accountsDatabase.put(accountOne.getAccountName(), accountOne);
		accountsDatabase.put(accountTwo.getAccountName(), accountTwo);
		accountsDatabase.put(accountThree.getAccountName(), accountThree);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		if(request.getParameter("logout")!=null){
			session.invalidate();
			response.sendRedirect("events");
			return;
		}
		
		else if(session.getAttribute("username") != null){
			response.sendRedirect("events");
			return;
		}
			
		String action = request.getParameter("action");
		
		if(action == null)
			action = "login";
		
		switch(action)
		{
			case "register":
				showAccountCreationForm(request, response);
				break;
			case "login":
			default:
				showLoginForm(request, response);
				break;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
	    
		if(action == null)
			action = "login";
		
		switch(action){
			case "register": 
				createAccount(request,response);
				break;
			case "login":
			default:
				authenticateAccount(request, response);
				break;
			
		}
	}

	private void authenticateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username") != null){
			response.sendRedirect("events");
			return;
		}
		
		boolean authenticated = false;
		
		String username = request.getParameter("accountName");
		String password = request.getParameter("accountPassword");
		
		
		
		if(accountsDatabase.containsKey(username) == true && accountsDatabase.get(username).getAccountPassword().equals(password)){
			authenticated = true;
		}
		
		if(authenticated == false){
			request.setAttribute("loginFailed", true);
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		else{
			HttpSession newSession = request.getSession();
			newSession.setAttribute("username", username);
			response.sendRedirect("events");
		}
	}

	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if (session.getAttribute("username") != null){
			System.out.println("I am in the session!");
			response.sendRedirect("events");
			return;
		}
		
		String username = request.getParameter("accountUsername");
		String email = request.getParameter("accountEmail");
		String password = request.getParameter("accountPassword");
		
		if(username.equals("") || email.equals("") || password.equals("")){
			request.setAttribute("createFailed", true);
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
		
		else if(accountsDatabase.containsKey(username)){
			request.setAttribute("duplicateName", true);
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);	
		}
		
		else {
		int id;
		synchronized(this){
			id = this.ACCOUNT_ID_SEQUENCE++;
		}
		
		ArrayList<Event> yourEvents = new ArrayList<>();
		Account account = new Account(id, username, email, password, yourEvents);
		accountsDatabase.put(account.getAccountName(), account);
		
		HttpSession newSession = request.getSession();
		newSession.setAttribute("username", username);
		response.sendRedirect("events");

		}
	}

	private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	private void showAccountCreationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/register.jsp")
        .forward(request, response);
		
	}
	
	public static Hashtable<String, Account> getAccountsDatabase() {
		return accountsDatabase;
	}

	public static void setAccountsDatabase(Hashtable<String, Account> aAccountsDatabase) {
		accountsDatabase = aAccountsDatabase;
	}




}
