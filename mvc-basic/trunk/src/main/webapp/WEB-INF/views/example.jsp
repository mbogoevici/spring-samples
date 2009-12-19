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
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">	
</head>	
<body>
<div class="container">
	<h1>
		@Controller Example
	</h1>
	<div class="span-24 last">	
		<form:form modelAttribute="testModel" method="post">
			<p>
				<form:label	for="text" path="text" cssErrorClass="error">Text</form:label><br/>
				<form:input path="text" /> <form:errors path="text" />			
			</p>
			<p>
				<form:label for="checkbox" path="checkbox">Checkbox</form:label><br/>
				<form:checkbox path="checkbox" />
			</p>
			<p>	
				<form:label for="currency" path="currency" cssErrorClass="error">Currency</form:label><br/>
				<form:input path="currency" /> <form:errors path="currency" />
			</p>
			<p>
				<form:label for="percent" path="percent" cssErrorClass="error">Percent</form:label><br/>
				<form:input path="percent" /> <form:errors path="percent" />
			</p>
			<p>	
				<form:label for="date" path="date" cssErrorClass="error">Date</form:label><br/>
				<form:input path="date" /> <form:errors path="date" />
			</p>
			<p>	
				<input type="submit" />
			</p>
		</form:form>
	</div>
	<hr>	
	<ul>
		<li> <a href="?locale=en_us">en</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
	</ul>	
</div>
</body>
</html>