<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Order</title>
</head>
<body>
    <form:form action="${pageContext.request.contextPath}/orders" modelAttribute="order" method="post"> 
    	<fieldset>
			<legend>Order data</legend> 				
			<div>
				<label for="symbol">Symbol:</label>
				<form:input path="symbol" size="10"/>			
			</div>
			
			<div>
				<label for="price">Price:</label>
				<form:input path="price" size="10"/>			
			</div>
					
			<input type="submit" value=Create />
			
		</fieldset>
    </form:form>
</body>
</html>