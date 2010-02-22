<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div id="dayPicker">

</div>

<div id="appointmentCalendar">
	<h2>
		<spring:eval expression="appointmentCalendar.day" />
	</h2>
	
	<div class="prev">
		<a href="<c:url value="/appointments?day=${appointmentCalendar.previousDay}"/>">Previous</a> 
	</div>
	<div class="next">
		<a href="<c:url value="/appointments?day=${appointmentCalendar.nextDay}"/>">Next</a>	
	</div>
	
	<table>
		<tr>
			<th>&nbsp;</th>
			<c:forEach var="doctor" items="${appointmentCalendar.doctors}">
				<th class="doctor" data-doctorId="${doctor.id}">Dr. ${doctor.label}</th>
			</c:forEach>
		</tr>
	
		<c:forEach var="block" items="${appointmentCalendar.blocks}" varStatus="i">
			<tr>
				<td>
					<joda:format value="${block}" style="-S" var="time" />
					${time}
				</td>
				<c:forEach var="doctor" items="${appointmentCalendar.doctors}" varStatus="j">
					<spring:eval expression="appointmentCalendar.appointments[i.count - 1][j.count - 1]" var="appointment" />
					<c:choose>
						<c:when test="${appointment != null}">
							<td class="filled" data-id="${appointment.id}" data-time="${time}" data-doctorId="${doctor.id}">
								<div class="patient">
									${appointment.patient}
								</div>
								<div class="client">
									${appointment.client} ${appointment.clientPhone}								
								</div>
								<div class="reason">
									${appointment.reason}
								</div>
							</td>				
						</c:when>
						<c:otherwise>
							<td class="open" data-time="${time}" data-doctorId="${doctor.id}">&nbsp;</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="addDialog" title="Add Appointment">
	<form id="addForm" action="${pageContext.request.contextPath}/appointments" method="POST">
		<fieldset>
			<p>
				When: <span id="when"></span>
			</p>
			<p>		
				<label for="patient">Patient</label><br/>
				<input id="patient" type="text" />
			</p>
			<p>
				<input type="submit" value="Add" />
			</p>
			<input type="hidden" name="patientId" />
			<input type="hidden" name="doctorId" />
			<input type="hidden" name="day" value="<joda:format value="${appointmentCalendar.day}" style="S-" />" />
			<input type="hidden" name="time" />
		</fieldset>
	</form>
</div>

<div id="updateDialog" title="Appointment">
	<div class="patient"></div>
	<hr/>
	<div class="link">
		Delete
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#dayPicker").datepicker({
			firstDay: 1,
			dateFormat: "yy-mm-dd",
			defaultDate: new Date(${appointmentCalendar.dayMillis}),
			onSelect: function(dateText, instance) {
				window.location = "${pageContext.request.contextPath}/appointments?day=" + dateText;
			}
		});

		$("#addDialog").dialog({
			autoOpen: false,
			modal: true,
			close: function(event, ui) {
				$('#addForm')[0].reset();
			}			
		});
		
		$("#updateDialog").dialog({
			autoOpen: false,
			modal: true
		});
			
		$("td.open").click(function() {
			$("#when").html($(this).attr("data-time"));
			$("#addForm input[name=time]").val($(this).attr("data-time"));
			$("#addForm input[name=doctorId]").val($(this).attr("data-doctorId"));
			$("#addForm input[type=submit]").attr("disabled", true);
			$("#addDialog").dialog("open");
			$("#patient").focus();
		});
			
		$("#patient").autocomplete({
			source: function(request, response) {
				$.getJSON("${pageContext.request.contextPath}/patients", { name: request.term }, response);
			},
			select: function(event, ui) {
				$("#addForm input[name=patientId]").val(ui.item.id);
				$("#addForm input[type=submit]").attr("disabled", false);
				$("#addForm input[type=submit]").focus();	
			}
		});

		$("td.filled").click(function() {
			$("#updateDialog .patient").html($(this).children(".patient").html());
			$("#updateDialog").attr("data-id", $(this).attr("data-id"));
			$("#updateDialog").dialog('option', 'title', $(this).attr("data-time"));			
			$("#updateDialog").dialog("open");
		});

		$("#updateDialog .link").click(function() {
			$.ajax({
				url: "${pageContext.request.contextPath}/appointments/" + $("#updateDialog").attr("data-id"),
				type: "DELETE",
				success: function(data) {
					$("#updateDialog").dialog('close');
					location.reload();	
				}
			});			
		});

		setTimeout(function(){
			console.log("Polling ${pageContext.request.contextPath}/appointments/messages?day=${appointmentCalendar.day}");
			$.getJSON("${pageContext.request.contextPath}/appointments/messages?day=${appointmentCalendar.day}", function(messages) {
				var i;
				for (i = 0; i < messages.length; i += 1) {
					var message = messages[i];
				}
			});
		    setTimeout(arguments.callee, 3000);
		  }, 10);
		
	});
</script>