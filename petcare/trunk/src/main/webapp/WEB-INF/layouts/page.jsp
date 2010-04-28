<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<title>
		<tiles:insertAttribute name="title" />
	</title>
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print" />
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection" />
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/resources/petcare.css" />" type="text/css" media="screen" />
	<tiles:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
	<c:forEach var="style" items="${styles}">
		<link rel="stylesheet" href="<c:url value="/resources/${style}" />" type="text/css" media="all" />
	</c:forEach>	
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
	<tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" ignore="true" />
	<c:forEach var="script" items="${scripts}">
		<script type="text/javascript" src="<c:url value="/resources/${script}" />"></script>	
	</c:forEach>	
</head>
<body>
	<div id="page" class="container">
		<div id="header" class="span-24 last">
			<div id="topbar" class="span-24 last">
				<p>
					<c:choose>
						<c:when test="${pageContext.request.userPrincipal != null}">
							${pageContext.request.userPrincipal.name} | <a href="<c:url value="/users/signout" />">Sign Out</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/users/signin" />">Sign In</a>
						</c:otherwise>
					</c:choose>
				</p>
			</div>
			<hr/>
			<div id="logo" class="span-24 last">
				<div class="span-6">
					<h1>Petcare</h1>
					<h2 class="alt">We love your pet</h2>
				</div>
				<div id="navigation" class="span-18 last">
					<ul>
						<li><a class="button" href="<c:url value="/" />">Home</a></li>
						<li><a class="button" href="<c:url value="/appointments" />">Appointments</a></li>
					</ul>
				</div>
			</div>
			<hr/>
		</div>
		<div id="content" class="span-24">
			<tiles:insertAttribute name="content" />
		</div>
		<hr/>
		<div id="footer" class="span-24">
			Copyright (c) 2010 SpringSource | About
		</div>
	</div>
</body>
</html>