<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>mvc-showcase</title>
</head>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.4.2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json2.js" />"></script>
<body>
<h1>mvc-showcase</h1>
<p>Recommended: Using a Web Developer tool such a Firebug to inspect the client/server interaction</p>
<div id="simple">
	<h2>Simple</h2>
	<p>
		See the <code>org.springframework.samples.mvc.simple</code> package for the @Controller code
	</p>
	<ul>
		<li>
			<a id="simple" class="textLink" href="<c:url value="/simple" />">GET /simple</a> <span id="simpleResponse"></span>
		</li>
		<li>
			<a id="simpleRevisited" class="textLink" href="<c:url value="/simple/revisited" />">GET /simple/revisited</a> <span id="simpleRevisitedResponse"></span>
		</li>
	</ul>
</div>
<div id="mapping">
	<h2>Mapping Requests</h2>
	<p>
		See the <code>org.springframework.samples.mvc.mapping</code> package for the @Controller code
	</p>
	<ul>
		<li>
			<a id="byPath" class="textLink" href="<c:url value="/mapping/path" />">By path</a> <span id="byPathResponse"></span>
		</li>
		<li>
			<a id="byMethod" class="textLink" href="<c:url value="/mapping/method" />">By path and method</a> <span id="byMethodResponse"></span>
		</li>
		<li>
			<a id="byParameter" class="textLink" href="<c:url value="/mapping/parameter?foo=bar" />">By path, method, and presence of parameter</a> <span id="byParameterResponse"></span>
		</li>
		<li>
			<a id="byNotParameter" class="textLink" href="<c:url value="/mapping/parameter" />">By path, method, and not presence of parameter</a> <span id="byNotParameterResponse"></span>
		</li>
		<li>
			<a id="byHeader" class="textLink" href="<c:url value="/mapping/header" />">By presence of header</a> <span id="byHeaderResponse"></span>
		</li>
		<li>
			<a id="byHeaderNegation" class="textLink" href="<c:url value="/mapping/notheader" />">By not presence of header</a> <span id="byHeaderNegationResponse"></span>			
		</li>		
		<li>
			<a id="byWildcard" class="textLink" href="<c:url value="/mapping/wildcard" />">By regexp</a> <span id="byWildcardResponse"></span>
		</li>		
	</ul>
</div>
<div id="data">
	<h2>Obtaining Request Data</h2>
	<p>
		See the <code>org.springframework.samples.mvc.data</code> package for the @Controller code
	</p>
	<ul>
		<li>
			<a id="param" class="textLink" href="<c:url value="/data/param?foo=bar" />">Query parameter</a> <span id="paramResponse"></span>
		</li>
		<li>
			<a id="group" class="textLink" href="<c:url value="/data/group?param1=foo&param2=bar&param3=baz" />">Group of query parameters</a> <span id="groupResponse"></span>
		</li>
		<li>
			<a id="var" class="textLink" href="<c:url value="/data/path/foo" />">Path variable</a> <span id="varResponse"></span>
		</li>
		<li>
			<a id="header" class="textLink" href="<c:url value="/data/header" />">Header</a> <span id="headerResponse"></span>
		</li>
		<li>
			<form id="requestBody" action="<c:url value="/data/body" />" method="post">
				<input type="submit" value="Request Body" /> <span id="requestBodyResponse"></span>		
			</form>
			<script type="text/javascript">
				$("form#requestBody").submit(function(){
					$.ajax({ type: "POST", url: this.action, data: "foo", contentType: "text/plain", dataType: "text",
						success: function(text) {
							$("#requestBodyResponse").text("").fadeIn().text(text); 
						}
					});					
					return false;
				});
			</script>
		</li>				
		<li>
			<form id="requestEntity" action="<c:url value="/data/entity" />" method="post">
				<input type="submit" value="Request Body and Headers" /> <span id="requestEntityResponse"></span>
			</form>
			<script type="text/javascript">
				$("form#requestEntity").submit(function(){
					$.ajax({ type: "POST", url: this.action, data: "foo", contentType: "text/plain", dataType: "text",
						success: function(text) {
							$("#requestEntityResponse").text("").fadeIn().text(text); 
						}
					});					
					return false;
				});
			</script>
		</li>
	</ul>	
	<div id="standardArgs">
		<h3>Standard Resolvable Web Arguments</h3>
		<ul>
			<li>
				<a id="request" class="textLink" href="<c:url value="/data/standard/request" />">Request arguments</a> <span id="requestResponse"></span>				
			</li>
			<li>
				<form id="requestReader" action="<c:url value="/data/standard/request/reader" />" method="post">
					<input type="submit" value="Request Reader" /> <span id="requestReaderResponse"></span>		
				</form>
				<script type="text/javascript">
					$("form#requestReader").submit(function(){
						$.ajax({ type: "POST", url: this.action, data: "foo", contentType: "text/plain", dataType: "text",
							success: function(text) {
								$("#requestReaderResponse").text("").fadeIn().text(text); 
							}
						});					
						return false;
					});
				</script>
			</li>			
			<li>
				<form id="requestIs" action="<c:url value="/data/standard/request/is" />" method="post">
					<input type="submit" value="Request InputStream" /> <span id="requestIsResponse"></span>		
				</form>
				<script type="text/javascript">
					$("form#requestIs").submit(function(){
						$.ajax({ type: "POST", url: this.action, data: "foo", contentType: "text/plain", dataType: "text",
							success: function(text) {
								$("#requestIsResponse").text("").fadeIn().text(text); 
							}
						});					
						return false;
					});
				</script>
			</li>
			<li>
				<a id="response" class="textLink" href="<c:url value="/data/standard/response" />">Response arguments</a> <span id="responseResponse"></span>				
			</li>			
			<li>
				<a id="writer" class="textLink" href="<c:url value="/data/standard/response/writer" />">Response Writer</a> <span id="writerResponse"></span>				
			</li>
			<li>
				<a id="os" class="textLink" href="<c:url value="/data/standard/response/os" />">Response OutputStream</a> <span id="osResponse"></span>					
			</li>
			<li>
				<a id="session" class="textLink" href="<c:url value="/data/standard/session" />">Session</a> <span id="sessionResponse"></span>			
			</li>			
		</ul>
	</div>
	<div id="customArgs">
		<h3>Custom Resolvable Web Arguments</h3>	
		<ul>
			<li>
				<a id="customArg" class="textLink" href="<c:url value="/data/custom" />">Custom</a> <span id="customArgResponse"></span>			
			</li>
		</ul>
	</div>
</div>
<div id="response">
	<h2>Writing Responses</h2>
	<p>
		See the <code>org.springframework.samples.mvc.response</code> package for the @Controller code
	</p>		
	<ul>
		<li>
			<a id="responseBody" class="textLink" href="<c:url value="/response/annotation" />">@ResponseBody</a> <span id="responseBodyResponse"></span>			
		</li>
		<li>
			<a id="responseEntityStatus" class="textLink" href="<c:url value="/response/entity/status" />">ResponseEntity (custom status)</a> <span id="responseEntityStatusResponse"></span>			
		</li>
		<li>
			<a id="responseEntityHeaders" class="textLink" href="<c:url value="/response/entity/headers" />">ResponseEntity (custom headers)</a> <span id="responseEntityHeadersResponse"></span>			
		</li>
	</ul>	
</div>
<div id="messageconverters">
	<h2>Http Message Converters</h2>
	<p>
		See the <code>org.springframework.samples.mvc.messageconverters</code> package for the @Controller code
	</p>	
	<div id="stringMessageConverter">
		<h3>StringHttpMessageConverter</h3>
		<ul>
			<li>
				<form id="readString" action="<c:url value="/messageconverters/string" />" method="post">
					<input type="submit" value="Read a String" /> <span id="readStringResponse"></span>		
				</form>
				<script type="text/javascript">
					$("form#readString").submit(function(){
						$.ajax({ type: "POST", url: this.action, data: "foo", contentType: "text/plain", dataType: "text",
							success: function(text) {
								$("#readStringResponse").text("").fadeIn().text(text); 
							}
						});					
						return false;
					});
				</script>
			</li>
			<li>
				<a id="writeString" class="textLink" href="<c:url value="/messageconverters/string" />">Write a String</a> <span id="writeStringResponse"></span>
			</li>
		</ul>
		<h3>FormHttpMessageConverter</h3>
		<ul>
			<li>
				<form id="readForm" action="<c:url value="/messageconverters/form" />" method="post">
					<input type="submit" value="Read a Form" /> <span id="readFormResponse"></span>		
				</form>
				<script type="text/javascript">
					$("#readForm").submit(function(){
						$.ajax({ type: "POST", url: this.action, data: "foo=bar&fruit=apple", contentType: "application/x-www-form-urlencoded", dataType: "text",
							success: function(text) {
								$("#readFormResponse").text("").fadeIn().text(text); 
							}
						});					
						return false;
					});
				</script>
			</li>
			<li>
				<a id="writeForm" href="<c:url value="/messageconverters/form" />">Write a Form</a> <span id="writeFormResponse"></span>
				<script type="text/javascript">
					$("#writeForm").click(function(){
						$.ajax({ url: this.href, dataType: "text",
							beforeSend: function(req) { 
								req.setRequestHeader("Accept", "application/x-www-form-urlencoded");
							},							
							success: function(text) {
								$("#writeFormResponse").text("").fadeIn().text(text); 
							}
						});					
						return false;
					});
				</script>								
			</li>
		</ul>
		<h3>Jaxb2RootElementHttpMessageConverter</h3>
		<ul>
			<li>
				<form id="readXml" action="<c:url value="/messageconverters/xml" />" method="post">
					<input type="submit" value="Read XML" /> <span id="readXmlResponse" class="responseMessage"></span>		
				</form>
				<script type="text/javascript">
					$("#readXml").submit(function(){
						$.ajax({ type: "POST", url: this.action, data: "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><javaBean><foo>bar</foo><fruit>apple</fruit></javaBean>", contentType: "application/xml", dataType: "text",
							success: function(text) {
								$("#readXmlResponse").text("").fadeIn().text(text); 
							}
						});					
						return false;
					});
				</script>
			</li>
			<li>
				<a id="writeXml" href="<c:url value="/messageconverters/xml" />">Write XML</a> <span id="writeXmlResponse"></span>
			</li>
		</ul>
		<h3>MappingJacksonHttpMessageConverter</h3>
		<ul>
			<li>
				<form id="readJson" action="<c:url value="/messageconverters/json" />" method="post">
					<input type="submit" value="Read JSON" /> <span id="readJsonResponse"></span>		
				</form>
				<script type="text/javascript">
					$("#readJson").submit(function(){
						$.ajax({ type: "POST", url: this.action, data: "{ \"foo\": \"bar\", \"fruit\": \"apple\" }", contentType: "application/json", dataType: "text",
							success: function(text) {
								$("#readJsonResponse").text("").fadeIn().text(text); 
							}
						});					
						return false;
					});
				</script>
			</li>
			<li>
				<a id="writeJson" href="<c:url value="/messageconverters/json" />">Write JSON</a> <span id="writeJsonResponse"></span>
				<script type="text/javascript">
					$("#writeJson").click(function(){
						$.ajax({ url: this.href, dataType: "json",
							success: function(json) {
								$("#writeJsonResponse").text("").fadeIn().text(JSON.stringify(json));
							}
						});					
						return false;
					});
				</script>								
			</li>
		</ul>
		<h3>AtomFeedHttpMessageConverter</h3>
		<ul>
			<li>
				<a id="writeFeed" href="<c:url value="/messageconverters/atom" />">Write Atom Feed</a> <span id="writeFeedResponse"></span>
				<script type="text/javascript">
					$("#writeFeed").click(function(){
						$.ajax({ url: this.href,
							beforeSend: function(req) { 
								req.setRequestHeader("Accept", "application/atom+xml")
							},
							success: function(feed) {
								$("#writeFeedResponse").text("").fadeIn().text($(feed).text());
							}
						});
						return false;
					});
				</script>								
			</li>
		</ul>
	</div>
</div>
<div id="views">
	<h2>Views</h2>
	<p>
		See the <code>org.springframework.samples.mvc.views</code> package for the @Controller code
	</p>
	<ul>
		<li>
			<a href="<c:url value="/views/html" />">HTML generated by JSP template</a>
		</li>
	</ul>	
	<ul>
		<li>
			<a href="<c:url value="/views/viewName" />">DefaultRequestToViewNameTranslator convention</a>
		</li>
	</ul>	
</div>
<div id="convert">
	<h2>Type Conversion</h2>
	<p>
		See the <code>org.springframework.samples.mvc.convert</code> package for the @Controller code
	</p>
	<ul>
		<li>
			<a id="primitive" class="textLink" href="<c:url value="/convert/primitive?value=3" />">Primitive</a> <span id="primitiveResponse"></span>
		</li>
		<li>
			<a id="date" class="textLink" href="<c:url value="/convert/date/2010-07-04" />">Date</a> <span id="dateResponse"></span>
		</li>
		<li>
			<a id="collection" class="textLink" href="<c:url value="/convert/collection?values=1&values=2&values=3&values=4&values=5" />">Collection 1</a> (multi-value parameter) <span id="collectionResponse"></span>
		</li>
		<li>
			<a id="collection2" class="textLink" href="<c:url value="/convert/collection?values=1,2,3,4,5" />">Collection 2</a> (single comma-delimited parameter value) <span id="collection2Response"></span>
		</li>
		<li>
			<a id="formattedCollection" class="textLink" href="<c:url value="/convert/formattedCollection?values=2010-07-04,2011-07-04" />">@Formatted Collection</a> <span id="formattedCollectionResponse"></span>
		</li>		
		<li>
			<a id="valueObject" class="textLink" href="<c:url value="/convert/value?value=123456789" />">Custom Value Object</a> <span id="valueObjectResponse"></span>
		</li>
		<li>
			<a id="customConverter" class="textLink" href="<c:url value="/convert/custom?value=123-45-6789" />">Custom Converter</a> <span id="customConverterResponse"></span>
		</li>		
	</ul>
	<div id="convert-bean">
		<h3>JavaBean Property Binding</h3>
		<ul>
			<li>
				<a id="primitiveProp" class="textLink" href="<c:url value="/convert/bean?primitive=3" />">Primitive</a> <span id="primitivePropResponse"></span>
			</li>	
			<li>
				<a id="dateProp" class="textLink" href="<c:url value="/convert/bean?date=2010-07-04" />">Date</a> <span id="datePropResponse"></span>
			</li>	
			<li>
				<a id="maskedProp" class="textLink" href="<c:url value="/convert/bean?masked=(205) 333-3333" />">Masked</a> <span id="maskedPropResponse"></span>
			</li>	
			<li>
				<a id="listProp" class="textLink" href="<c:url value="/convert/bean?list[0]=1&list[1]=2&list[2]=3" />">List Elements</a> <span id="listPropResponse"></span>
			</li>
			<li>
				<a id="formattedListProp" class="textLink" href="<c:url value="/convert/bean?formattedList[0]=2010-07-04&formattedList[1]=2011-07-04" />">@Formatted List Elements</a> <span id="formattedListPropResponse"></span>
			</li>
			<li>
				<a id="mapProp" class="textLink" href="<c:url value="/convert/bean?map[0]=apple&map[1]=pear" />">Map Elements</a> <span id="mapPropResponse"></span>
			</li>
			<li>
				<a id="nestedProp" class="textLink" href="<c:url value="/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip" />">Nested</a> <span id="nestedPropResponse"></span>
			</li>
		</ul>
	</div>
</div>
<div id="validation">
	<h2>Validation</h2>
	<p>
		See the <code>org.springframework.samples.mvc.validation</code> package for the @Controller code	
	</p>
	<ul>
		<li>
			<a id="validateNoErrors" class="textLink" href="<c:url value="/validate?number=3&date=2029-07-04" />">Validate, no errors</a> <span id="validateNoErrorsResponse"></span>
		</li>
		<li>
			<a id="validateErrors" class="textLink" href="<c:url value="/validate?number=3&date=2010-07-01" />">Validate, errors</a> <span id="validateErrorsResponse"></span>
		</li>
	</ul>	
</div>
<div id="exceptions">
	<h2>Exception Handling</h2>
	<p>
		See the <code>org.springframework.samples.mvc.exceptions</code> package for the @Controller code	
	</p>
	<ul>
		<li>
			<a id="exception" class="textLink" href="<c:url value="/exception" />">@Controller Exception handling</a> <span id="exceptionResponse"></span>
		</li>
	</ul>
</div>
<script type="text/javascript">
$("a[class=textLink]").click(function(){
	var responseElement = "#" + this.id + "Response";
	$.ajax({ url: this.href, dataType: "text",
		success: function(text) {
			$(responseElement).text("").fadeIn().text(text); 
		},
		error: function(xhr) {
			$(responseElement).text("").fadeIn().text(xhr.responseText); 
		}
	});				
	return false;
});
</script>
</body>
</html>