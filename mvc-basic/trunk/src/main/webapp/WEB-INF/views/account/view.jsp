<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>@Controller Example</title>
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
</head>	
<body>
<div class="container">
	<h1>
		View Account
	</h1>
	<div class="span-12 last">	
		<form:form modelAttribute="account" action="${account.id}" method="post">
		  	<fieldset>		
				<legend>Account Fields</legend>
				<p>
					<form:label	for="name" path="name">Name</form:label><br/>
					<form:input path="name" readonly="true"/>		
				</p>
				<p>	
					<form:label for="balance" path="balance">Balance</form:label><br/>
					<form:input path="balance" readonly="true"/>
				</p>
				<p>
					<form:label for="equityAllocation" path="equityAllocation">Equity Allocation</form:label><br/>
					<form:input path="equityAllocation" readonly="true"/>
				</p>
				<p>	
					<form:label for="renewalDate" path="renewalDate">Renewal Date</form:label><br/>
					<form:input path="renewalDate" readonly="true"/>
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