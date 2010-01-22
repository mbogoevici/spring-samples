<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Create Account</title>
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/screen.css" />" type="text/css" media="screen, projection">
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">
		<!--[if lt IE 8]>
			<link rel="stylesheet" href="<c:url value="/styles/blueprint/ie.css" />" type="text/css" media="screen, projection">
		<![endif]-->
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">
		<script type="text/javascript" src="<c:url value="/scripts/jquery-1.4.min.js" /> "></script>
		<script type="text/javascript" src="<c:url value="/scripts/json.min.js" /> "></script>
	</head>
	<body>
		<div class="container">
			<h1>
				Create Account
			</h1>
			<div class="span-12 last">	
				<form:form modelAttribute="account" method="post">
				  	<fieldset>		
						<legend>Account Fields</legend>
						<p>
							<form:label	id="nameLabel" for="name" path="name" cssErrorClass="error">Name</form:label><br/>
							<form:input path="name" /><form:errors path="name" />
						</p>
						<p>	
							<form:label for="balance" path="balance" cssErrorClass="error">Balance</form:label><br/>
							<form:input path="balance" /><form:errors path="balance" />
						</p>
						<p>
							<form:label for="equityAllocation" path="equityAllocation" cssErrorClass="error">Equity Allocation</form:label><br/>
							<form:input path="equityAllocation" /><form:errors path="equityAllocation" />
						</p>
						<p>	
							<form:label for="renewalDate" path="renewalDate" cssErrorClass="error">Renewal Date</form:label><br/>
							<form:input path="renewalDate" /><form:errors path="renewalDate" />
						</p>
						<p>	
							<input id="create" type="submit" value="Create" />
						</p>
					</fieldset>
				</form:form>
			</div>
			<hr>	
			<ul>
				<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
			</ul>	
		</div>	
	</body>

	<script type="text/javascript">	
		$(document).ready(function() {
			$('#name').blur(function() {
				validateNameField();
			});
			$("#account").submit(function() {
				var account = $(this).serializeObject();
				$.postJSON("account", account, function(data) {
					console.log(data);
				});
				return false;				
			});
		});

		function validateNameField() {
			var val = $('#name').val();
			if (!val) {
				return { valid : true };
			}
			$.getJSON("account/availability", { name: val }, function(availability) {
				if (availability.available) {
					fieldValidated( "name", { valid : true });
				} else {
					fieldValidated( "name", { valid : false, message : val + " is not  available, try " + availability.suggestions });
				}
			});
		}

		function fieldValidated(field, result) {
			if (result.valid) {
				$("#" + field + "Label").removeClass("error");
				$("#" + field + "\\.errors").remove();
				$('#create').attr("disabled", false);
			} else {
				$("#" + field + "Label").addClass("error");
				if ($("#" + field + "\\.errors").length == 0) {
					$("#" + field).after("<span id='" + field + ".errors'>" + result.message + "</span>");		
				} else {
					$("#" + field + "\\.errors").html("<span id='" + field + ".errors'>" + result.message + "</span>");		
				}
				$('#create').attr("disabled", true);					
			}			
		}
	</script>
	
</html>
