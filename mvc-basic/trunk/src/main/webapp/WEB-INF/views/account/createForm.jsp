<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Create Account</title>
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
</head>	
<body>
<div class="container">
	<h1>
		Create Account
	</h1>
	<div class="span-12 last">	
		<form:form modelAttribute="account" action="account" method="post">
		  	<fieldset>		
				<legend>Account Fields</legend>
				<p>
					<form:label	for="name" path="name" cssErrorClass="error">Name</form:label><br/>
					<form:input path="name" /> <form:errors path="name" />			
				</p>
				<p>	
					<form:label for="balance" path="balance" cssErrorClass="error">Balance</form:label><br/>
					<form:input path="balance" /> <form:errors path="balance" />
				</p>
				<p>
					<form:label for="equityAllocation" path="equityAllocation" cssErrorClass="error">Equity Allocation</form:label><br/>
					<form:input path="equityAllocation" /> <form:errors path="equityAllocation" />
				</p>
				<p>	
					<form:label for="renewalDate" path="renewalDate" cssErrorClass="error">Renewal Date</form:label><br/>
					<form:input path="renewalDate" /> <form:errors path="renewalDate" />
				</p>
				<p>	
					<input type="submit" />
				</p>
			</fieldset>
		</form:form>
	</div>
	<hr>	
	<ul>
		<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
	</ul>	
</div>
</body>
</html>