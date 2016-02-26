package mycalendar.servlets;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private volatile int ACCOUNT_ID_SEQUENCE = 4;
	
	private Hashtable<String,String> accountsDatabase = new Hashtable<>();

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		Account accountOne = new Account(1, "andrew", "andrew@gmail.com", "test12");
		Account accountTwo = new Account(2, "bob", "bobson@gmail.com", "test12");
		Account accountThree = new Account(3, "chang", "chang@gmail.com", "test12");
		
		accountsDatabase.put(accountOne.getAccountName(), accountOne.getAccountPassword());
		accountsDatabase.put(accountTwo.getAccountName(), accountTwo.getAccountPassword());
		accountsDatabase.put(accountThree.getAccountName(), accountThree.getAccountPassword());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
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
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("username") != null){
			response.sendRedirect("events");
			return;
		}
		
		boolean authenticated = false;
		
		String username = request.getParameter("accountName");
		String password = request.getParameter("accountPassword");
		
		if(accountsDatabase.containsKey(username) && accountsDatabase.get(username).equals(password)){
			authenticated = true;
		}
		
		if(authenticated == false){
			request.setAttribute("loginFailed", true);
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		else{
			session.setAttribute("username", username);
			request.changeSessionId();
			response.sendRedirect("events");
		}
	}

	private void createAccount(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	private void showAccountCreationForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}



}
