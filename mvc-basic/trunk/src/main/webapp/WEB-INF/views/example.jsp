<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>@Controller Example</title>
</head>
<body>
<h1>
	@Controller Example
</h1>

<form:form modelAttribute="testModel" method="post">

	<form:label	for="text" path="text">
		Text <form:input path="text" /> <form:errors path="text" />			
	</form:label> 
	
	<form:label for="checkbox" path="checkbox">
		Checkbox <form:checkbox path="checkbox" />
	</form:label> 
		
	<form:label for="currency" path="currency">
		Currency <form:input path="currency" /> <form:errors path="currency" />
	</form:label>
		
	<form:label for="percent" path="percent">
		Percent <form:input path="percent" /> <form:errors path="percent" />
	</form:label>
		
	<form:label for="date" path="date">
		Date <form:input path="date" /> <form:errors path="date" />
	</form:label>
			
	<input type="submit" />
	
</form:form>

<ul>
	<li> <a href="?locale=en_us">en</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
</ul>

</body>
</html>