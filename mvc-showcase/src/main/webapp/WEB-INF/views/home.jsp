<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>mvc-showcase</title>
</head>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.4.2.js" />"></script>
<body>
<h1>mvc-showcase</h1>
<div id="simple">
	<ul>
		<li>
			<a href="<c:url value="/simple" />">GET /simple</a>
		</li>
		<li>
			<a id="simpleTextOnly" href="<c:url value="/simple/textonly" />">GET /simple/textonly</a>
			<script type="text/javascript">
				$("#simpleTextOnly").click(function(){
					$.ajax({ url: "simple/textonly", dataType: "text", success: function(text) {
							$("#simpleTextOnlyResponse").text("").fadeIn().text(text); 
						}
					});					
					return false;
				});
			</script>
			<span id="simpleTextOnlyResponse" class="responseMessage"></span>
		</li>
		<li>
			<a href="<c:url value="/simple/textonly" />">GET /simple/textonly</a> (404 due to wrong Accept header)	
		</li>
	</ul>
</div>
<div id="mapping">

</div>
<div id="data">

</div>
<div id="response">

</div>
<div id="messageconverters">

</div>
<div id="views">

</div>
<div id="convert">

</div>
<div id="validation">

</div>
<div id="exceptions">

</div>
</body>
</html>