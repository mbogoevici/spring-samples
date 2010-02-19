<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div id="dayPicker">

</div>

<div id="calendar">

	<h2>
		<spring:eval expression="appointmentCalendar.day" />
	</h2>
	
	<div class="prev">
		<a href="<c:url value="/appointments/${appointmentCalendar.previousDayResourceId}"/>">Previous</a> 
	</div>
	<div class="next">
		<a href="<c:url value="/appointments/${appointmentCalendar.nextDayResourceId}"/>">Next</a>	
	</div>
	
	<table>
		<tr>
			<th>&nbsp;</th>
			<c:forEach var="doctor" items="${appointmentCalendar.doctors}">
				<th>Dr. ${doctor.label}</th>
			</c:forEach>
		</tr>
	
		<c:forEach var="block" items="${appointmentCalendar.blocks}" varStatus="b">
			<tr>
				<td>
					<spring:eval expression="block.time" />
				</td>
				<c:forEach var="doctor" items="${appointmentCalendar.doctors}" varStatus="d">
					<spring:eval expression="appointmentCalendar.appointments[b.count - 1][d.count - 1]" var="appointment" />
					<c:choose>
						<c:when test="${appointment != null}">
							<td class="filled" data-id="${appointment.id}">
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
							<td class="open" data-time="<spring:eval expression="block.time" />" data-doctorId="${doctor.id}">&nbsp;</td>
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
				When: <span id="selectedBlock"></span>
			</p>
			<p>		
				<label for="patient">Patient</label><br/><input type="text" />
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

<div id="appDialog"">
	<p>
		<span id="patient"></span>
	</p>
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
				window.location = "${pageContext.request.contextPath}/appointments/" + dateText;
			}
		});
		
		$("td.open").click(function() {
			var time = $(this).attr("data-time");
			$("#selectedBlock").html(time);
			$("#time").val(time);
			$("#doctorId").val($(this).attr("data-doctorId"));
			$("#add").attr("disabled", true);
			$("#addDialog").dialog("open");
			$("#patient").focus();
		});

		$("td.filled").click(function() {
			
		});
			
		$("#addDialog").dialog({
			autoOpen: false,
			modal: true,
			close: function(event, ui) {
				$('#addForm')[0].reset();
			}			
		});

		$("#patient").autocomplete({
			source: function(request, response) {
				$.getJSON("${pageContext.request.contextPath}/patients", { name: request.term }, response);
			},
			select: function(event, ui) {
				$("#addForm").elements["patientId"].val(ui.item.id);
				$("#add").attr("disabled", false);				
			}
		});
		
	});
</script>