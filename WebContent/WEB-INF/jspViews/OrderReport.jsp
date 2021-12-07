<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Report</title>
</head>
<body>
<center>
	<h2>Order with in Price Range Report</h2>
	<form:form modelAttribute="bRange" action="fetchOrderDetails.html" method="POST">
		<table border="3">
			<tr>
				<th>Form Price: </th><td><form:input path="fromPrice"/></td>
				<th>To Price: </th><td><form:input path="toPrice"/></form></td>
			</tr>
		</table>
		<br>
		<br>
		<input type="submit" value="Fetch Details">
		<br>
		<c:if test="${not empty pizzaOrderList}">
			<table border="2">
				<tr>
					<th>Order Id</th><th>Customer Name</th><th>Pizza Id</th><th>Bill</th><th>Quantity</th>
				</tr>
				<c:forEach items="${pizzaOrderList}" var="pord">
					<tr>
						<td><c:out value="${pord.orderId}"></c:out></td>
						<td><c:out value="${pord.customerName}"></c:out></td>
						<td><c:out value="${pord.pizzaId}"></c:out></td>
						<td> <fmt:formatNumber value="${pord.bill}" type="currency" currencySymbol="INR." ></fmt:formatNumber></td>
						<td><c:out value="${pord.numberOfPiecesOrdered}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br>
		<a href="${pageContext.request.contextPath}/index.jsp">Home</a>	
	</form:form>
</center>
</body>
</html>