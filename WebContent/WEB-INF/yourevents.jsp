<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList, mycalendar.model.Event, mycalendar.model.Account, java.util.Date" %>

<%
    @SuppressWarnings("unchecked")
    ArrayList<Event> eventsDatabase =
            (ArrayList<Event>)request.getAttribute("associatedEvents");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Events</title>
</head>
<body>
<p>Your Events are here ! </p>
<% if (request.getAttribute("loggedIn") != null )
	{%>
<a href="<c:url value='/login'>
      <c:param name='logout' value='true' />
      </c:url>">Logout</a>
<a href="<c:url value='/login'>
     </c:url>">Back</a>
<% } %>

<%
	if(eventsDatabase.size() == 0){ %>
			<p><i>We are Sorry !There are no events to show you right now!</i></p>
<% 	}
	else{
		for(Event event: eventsDatabase){
			String name = event.getEventName();
			String creator = event.getEventCreater();
			String date = event.getEventTime();
			String location = event.getLocation();
			%>
			<div class="row">
				<div class="col-md-3">
				<p>Event: <%= name %> </p>
			    </div>
			    <div class="col-md-3">
				 <p>Creator: <%= creator %> </p>
			    </div>
			    <div class="col-md-3">
				 <p>Time: <%= date %> </p>
				 </div>
				 <div class="col-md-3">
				 <p>Location: <%= location %> </p>
				 </div>
			</div>
		<% }
	}
%>
</body>
</html>