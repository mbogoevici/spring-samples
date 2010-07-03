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
	<h2>Simple</h2>
	<p>
		See the <code>org.springframework.samples.mvc.simple</code> package for the @Controller code
	</p>
	<ul>
		<li>
			<a href="<c:url value="/simple" />">GET /simple</a>
		</li>
		<li>
			<a id="simpleTextOnly" href="<c:url value="/simple/textonly" />">GET /simple/textonly</a>
			<script type="text/javascript">
				$("#simpleTextOnly").click(function(){
					$.ajax({ url: this.href, dataType: "text", success: function(text) {
							$("#simpleTextOnlyResponse").text("").fadeIn().text(text); 
						}
					});					
					return false;
				});
			</script>
			<span id="simpleTextOnlyResponse" class="responseMessage"></span>
		</li>
		<li>
			<a href="<c:url value="/simple/textonly" />">GET /simple/textonly</a> (Error due to wrong Accept header)	
		</li>
	</ul>
</div>
<div id="mapping">
	<h2>Request Mapping</h2>
	<p>
		See the <code>org.springframework.samples.mvc.mapping</code> package for the @Controller code
	</p>
	<ul>
		<li>
			<a href="<c:url value="/mapping/path" />">Mapping by path</a>
		</li>
		<li>
			<a href="<c:url value="/mapping/method" />">Mapping by path and method</a>
		</li>
		<li>
			<a href="<c:url value="/mapping/parameter?foo=bar" />">Mapping by path, method, and presence of parameter</a>
		</li>
		<li>
			<a href="<c:url value="/mapping/parameter" />">Mapping by path, method, and not presence of parameter</a>
		</li>
		<li>
			<a id="mappingByHeader" href="<c:url value="/mapping/header" />">Mapping by presence of header</a>
			<script type="text/javascript">
				$("#mappingByHeader").click(function(){
					$.ajax({ url: this.href, dataType: "text", success: function(text) {
							$("#mappingByHeaderTextResponse").text("").fadeIn().text(text); 
						}
					});					
					return false;
				});
			</script>
			<span id="mappingByHeaderTextResponse" class="responseMessage"></span>
		</li>
		<li>
			<a id="mappingByHeaderNegation" href="<c:url value="/mapping/header" />">Mapping by not presence of header</a>
		</li>		
		<li>
			<a href="<c:url value="/mapping/wildcard" />">Mapping by regexp</a>
		</li>		
	</ul>
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