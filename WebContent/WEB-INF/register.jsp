<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
<form action="login" method="POST">
<% 
	if ( request.getAttribute("createFailed") != null && (Boolean)request.getAttribute("createFailed") )
	{
%>
	<p style="color: red;">You must fill out all of the fields!</p>
<%  } 
	if (request.getAttribute("duplicateName") != null && (Boolean)request.getAttribute("duplicateName"))
	{
	%>
	<p style="color: red;">Account Name Already Exists!</p>
<%  } %>
	<p>Username: </p><input type="text" name="accountUsername" />  
	<p>Email: </p><input type="text" name="accountEmail" />  
	<p>Password: </p><input type="password" name="accountPassword" /><br /><br />  
	<input type="hidden" name="action" value="register" />
	<input type="submit" />
</form>
<a href="<c:url value='/login' />">Login</a>
</body>
</html>