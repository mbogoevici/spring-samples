<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<title>Create Account</title>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
		<!--[if lt IE 8]>
			<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection">
		<![endif]-->
		<link rel="stylesheet" href="<c:url value="/resources/popup.css" />" type="text/css" media="screen, projection">
		<script type="text/javascript" src="<c:url value="/resources/jquery-1.4.min.js" /> "></script>
		<script type="text/javascript" src="<c:url value="/resources/json.min.js" /> "></script>
	</head>
	<body>
		<div class="container">
			<h1>
				Create Account
			</h1>
			<div class="span-12 last">	
				<form:form modelAttribute="account" action="account" method="post">
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
		<div id="mask" style="display: none;"></div>
		<div id="popup" style="display: none;">
			<div class="span-8 last">
				<h3>Account Created Successfully</h3>
				<form>
					<fieldset>
						<p>
							<label for="assignedId">Assigned Id</label><br/>
							<input id="assignedId" type="text" readonly="readonly" />		
						</p>
						<p>
							<label for="confirmedName">Name</label><br/>
							<input id="confirmedName" type="text" readonly="readonly" />
						</p>
						<p>	
							<label for="confirmedBalance">Balance</label><br/>
							<input id="confirmedBalance" type="text" readonly="readonly" />
						</p>
						<p>	
							<label for="confirmedRenewalDate">Renewal Date</label><br/>
							<input id="confirmedRenewalDate" type="text" readonly="readonly" />
						</p>
					</fieldset>
				</form>
				<a href="#" onclick="closePopup();">Close</a>			
			</div>			
		</div>		
	</body>

	<script type="text/javascript">	
		$(document).ready(function() {
			// check name availability on focus lost
			$('#name').blur(function() {
				if ($('#name').val()) {	
					checkAvailability();
				}
			});
			$("#account").submit(function() {
				var account = $(this).serializeObject();
				$.postJSON("account", account, function(data) {
					$("#assignedId").val(data.id);
					showPopup();
				});
				return false;				
			});
		});

		function checkAvailability() {
			$.getJSON("account/availability", { name: $('#name').val() }, function(availability) {
				if (availability.available) {
					fieldValidated("name", { valid : true });
				} else {
					fieldValidated("name", { valid : false, message : $('#name').val() + " is not available, try " + availability.suggestions });
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

		function showPopup() {
			$.getJSON("account/" + $("#assignedId").val(), function(account) {
				$("#confirmedName").val(account.name);
				$("#confirmedBalance").val(account.balance);
				$("#confirmedEquityAllocation").val(account.equityAllocation);
				$("#confirmedRenewalDate").val(account.renewalDate);
			});			
			$('body').css('overflow','hidden');
			$('#popup').fadeIn('fast');
			$('#mask').fadeIn('fast');
		}
		
		function closePopup() {
			$('#popup').fadeOut('fast');
			$('#mask').fadeOut('fast');
			$('body').css('overflow','auto');
			resetForm();
		}

		function resetForm() {
			$('#account')[0].reset();
		}
		
	</script>
	
</html>
