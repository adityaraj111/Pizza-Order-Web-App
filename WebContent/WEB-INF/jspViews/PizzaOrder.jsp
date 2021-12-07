<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pizza Order</title>
<style>
	.error{
		font-style: italic;
		color: red;
	}
</style>
</head>
<body>
<center>
	<h2>Add Pizza Details</h2>
	<form:form modelAttribute="piz" action="${pageContext.request.contextPath}/savePizzaOrder.html" method="POST">
		<table border="3">
			<tr>
				<th>Customer Name</th>
				<td><form:input path="customerName"/></td>
			</tr>
			<tr>
				<th>Customer Contact</th>
				<td><form:input path="contactNumber"/></td>
			</tr>
			<tr>
				<th>Pizza Name</th>
				<td><form:select path="pizzaId">
					<form:option label="--Select--" value=""></form:option>
					<form:options items="${pizzaIdAndName}"/>
				</form:select></td>
			</tr>
			<tr>
				<th>Quantity</th>
				<td><form:input path="numberOfPiecesOrdered"/></td>
			</tr>
		</table>
		<br></br>
		<input type="submit" value="order">
		<br />
		<br />
			<a href="${pageContext.request.contextPath}/index.jsp"> Home</a>
			<spring:hasBindErrors name="piz">
			<h3>All Errors</h3>
			<form:errors path="*"  cssClass="error"/>
	   		</spring:hasBindErrors>
	</form:form>
</center>

</body>
</html>