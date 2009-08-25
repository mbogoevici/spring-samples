<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Spring Travel: Spring MVC and Web Flow Reference Application</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/dijit/themes/tundra/tundra.css" />" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/dojox/grid/resources/Grid.css" />" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/dojox/grid/resources/tundraGrid.css" />" />
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/styles/general.css" />" />
	
	
	<!--  <style type="text/css">
		@import "dijit/themes/tundra/tundra.css";
		@import "dojox/grid/resources/Grid.css";
		@import "dojox/grid/resources/tundraGrid.css";
		@import "general.css";
	</style>-->
	
    <script type="text/javascript" src="<c:url value="/resources/dojo/dojo.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/spring/Spring-Dojo.js" />"></script>
    
    <tiles:insertAttribute name="pageScripts" ignore="true"/>
</head>
<body class="tundra">

			<tiles:insertAttribute name="body" />

</body>
</html>