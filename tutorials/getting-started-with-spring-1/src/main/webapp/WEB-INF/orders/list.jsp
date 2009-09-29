<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Of sStock Orders</title>
</head>
<body>
<h1>List of Stock Orders</h1>
<table>
	<c:forEach items="${orderList}" var="order">
		<tr>
			<td>${order.symbol}</td>
			<td>${order.price}</td>
		</tr>
	</c:forEach>
</table>
</br>
</br>
<a href="${pageContext.request.contextPath}/orders/create">Create New Order</a>
</body>
</html>