<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<% 
	if ( request.getAttribute("loginFailed") != null && (Boolean)request.getAttribute("loginFailed") )
	{
%>
	<p style="color: red;">Incorrect username or password!</p>
<%  }  %>
<form action="login" method="post">
<input type="hidden" name="action" value="login">
User-name : <input type="text" name="accountName"> <br>
Password : <input type="password" name="accountPassword"> <br>
<input type="submit" value="login">
</form>
</body>
</html>