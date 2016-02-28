<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<% 
	if ( request.getAttribute("loginFailed") != null && (Boolean)request.getAttribute("loginFailed") == true )
	{
%>
	<p style="color: red;">Incorrect username or password!</p>
<%  }  %>
<form action="login" method="post">
<input type="hidden" name="action" value="login">
User-name : <input type="text" name="accountName"> <br>
Password : <input type="password" name="accountPassword"> <br>
<input type="submit" value="login"> <br>
</form>
<br>
<a href="<c:url value='/login'>
            <c:param name='action' value='register' />
         </c:url>">Create Account</a><br />
</body>
</html>