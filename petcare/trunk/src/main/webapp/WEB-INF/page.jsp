<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<title><tiles:insertAttribute name="title"/></title>
	<link href="${pageContext.request.contextPath}/resources/styles/main.css"  rel="stylesheet" type="text/css"/>
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
						<li><a href="<c:url value="/account/signin"/>">Sign In</a></li>		
					</c:otherwise>
				</c:choose>
			</div>
			<div id="branding">
				<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/images/banner-graphic.png" alt="Spring Petclinic" /></a>
			</div>
		</div>
		<div id="content">
			<div id="main">
				<tiles:insertAttribute name="content"/>
			</div>
			<div id="local">
				<tiles:insertAttribute name="localNavigation"/>
			</div>
			<div id="nav">
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/appointments">Appointments</a></li>
					<li><a href="${pageContext.request.contextPath}/clients">Clients</a></li>
				</ul>
			</div>
		</div>
		<div id="footer">
			<ul id="legal">
				<li>Privacy Policy</li>
				<li>Terms of Service</li>
				<li><a href="http://www.springsource.org"><img src="${pageContext.request.contextPath}/resources/images/springsource-logo.png" alt="Powered by SpringSource" /></a> </li>
			</ul>	
			
		</div>
	</div>
</body>
</html>