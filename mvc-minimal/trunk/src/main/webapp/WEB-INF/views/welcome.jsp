<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<html>
<head>
	<title><fmt:message key="welcome.title"/></title>
</head>
<body>
<h1>
	<fmt:message key="welcome.title"/>
</h1>
<p>
	Locale = ${pageContext.response.locale}
</p>
<ul>
	<li> <a href="?locale=en">en</a> | <a href="?locale=es">es</a> | <a href="?locale=de">de</a> </li>
</ul>
</body>
</html>