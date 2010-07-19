<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>fileupload | mvc-showcase</title>
	<link rel="stylesheet" href="<c:url value="/resources/form.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/messages/messages.css" />" type="text/css" media="screen" />
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.4.2.js" />"></script>
</head>
<body>
<h1>fileupload-showcase</h1>
<p>
	See the <code>org.springframework.samples.mvc.fileupload</code> package for the @Controller code	
</p>
<div id="fileupload">
	<form action="fileupload" method="POST" enctype="multipart/form-data">
		<div class="header">
	  		<h2>Form</h2>
	  		<c:if test="${not empty message}">
				<div class="${message.type}">${message.text}</div>	  		
	  		</c:if>
		</div>
		<label for="file">
			File
		</label>
		<input id="file" type="file" name="file" />
		<input type="submit" value="Upload">
	</form>	
</div>
</body>
</html>