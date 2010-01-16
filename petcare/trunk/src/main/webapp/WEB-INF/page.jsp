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
</head>
<body id="page-body">
	<div id="page">
		<div id="header">
			<div id="signin">
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal != null}">
						<p>Welcome ${pageContext.request.userPrincipal.name}</p>
						<li><a href="<c:url value="/account/signout"/>">Sign Out</a></li>
					</c:when>
					<c:otherwise>
						<!-- TODO SIGN IN -->
					</c:otherwise>
				</c:choose>
			</div>
			<div id="branding">
				<a href="<c:url value="/"/>"><img src="<c:url value="/images/banner-graphic.png"/>" alt="Spring Petcare" /></a>
			</div>
		</div>
		<div id="content">
			<div id="main">
				<tiles:insertAttribute name="content"/>
			</div>
			<div id="local">
				<ul>
					<tiles:insertAttribute name="localNavigation"/>
				</ul>
			</div>
			<div id="nav">
				<ul>
					<li><a href="<c:url value="/"/>">Home</a></li>
					<li><a href="<c:url value="/appointments"/>">Appointments</a></li>
					<!-- TODO Clients -->
				</ul>
			</div>
		</div>
		<div id="footer">
			<ul id="legal">
				<li>Privacy Policy</li>
				<li>Terms of Service</li>
				<li><a href="http://www.springsource.org"><img src="<c:url value="/images/springsource-logo.png"/>" alt="Powered by SpringSource" /></a> </li>
			</ul>
		</div>
	</div>
</body>
</html>