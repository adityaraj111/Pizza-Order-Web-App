<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pizza Order Success</title>
</head>
<body>
<center>
	<h2>Add Pizza Order Details Success</h2>
	<h3>${message}</h3>
	<br>
	<a href="${pageContext.request.contextPath}/index.jsp"> Home</a>
</center>

</body>
</html>