<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Event</title>
</head>
<body>
<p>Create Your Event Here !</p>

<% if (request.getAttribute("loggedIn") != null )
	{%>
<a href="<c:url value='/login'>
            <c:param name='logout' value='true' />
         </c:url>">Logout</a><br /><br /> 
<% } %>

<form action="events" method="post">
<input type="hidden" name="action" value="create">
Event Name : <input type="text" name="eventName">
Event Time : <input type="text" name="eventTime">
Location : <input type="text" name="eventLocation">
<input type="submit" value="events">
</form>
</body>
</html>