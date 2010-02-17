<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<title>
		<tiles:insertAttribute name="title"/>
	</title>
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/screen.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print" />
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/ie.css" />" type="text/css" media="screen, projection" />
	<![endif]-->
	<tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" ignore="true" />
	<c:forEach var="script" items="${scripts}">
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/${script}"></script>	
	</c:forEach>	
</head>
<body id="page-body">
	<div id="page" class="container">
		<div id="header" class="span-24 last">
			<div id="signin">
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal != null}">
						<p>Welcome ${pageContext.request.userPrincipal.name}</p>
						<li><a href="<c:url value="/account/signout"/>">Sign Out</a></li>
					</c:when>
					<c:otherwise>
						<!-- TODO sign in -->
					</c:otherwise>
				</c:choose>
			</div>
			<div id="branding">
				<!-- TODO branding -->
			</div>
		</div>
		<div id="content">
			<div id="nav" class="span-24">
				<ul>
					<li><a href="<c:url value="/"/>">Home</a></li>
					<li><a href="<c:url value="/appointments"/>">Appointments</a></li>
					<!-- TODO Clients -->
				</ul>
			</div>
			<div id="local" class="span-4">
				<ul>
					<tiles:insertAttribute name="localNavigation"/>
				</ul>
			</div>
			<div id="main" class="span-16">
				<tiles:insertAttribute name="content"/>
			</div>
		</div>
		<div id="footer" class="span-24">
			<ul id="legal">
			
			</ul>
		</div>
	</div>
</body>
</html>