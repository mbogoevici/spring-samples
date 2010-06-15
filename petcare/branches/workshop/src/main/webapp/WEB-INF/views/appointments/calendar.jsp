<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div id="appointmentDay" class="prepend-6 span-4 alt">
	<spring:eval expression="appointmentCalendar.day" />
</div>

<div id="appointmentNavigation" class="span-3 append-11 last alt">
	<c:url var="previousLink" value="/appointments">
		<c:param name="day" value="${appointmentCalendar.previousDay}" />
	</c:url>
	<a href="${previousLink}">Previous</a>
	
	<c:url var="nextLink" value="/appointments">
		<c:param name="day" value="${appointmentCalendar.nextDay}" />
	</c:url>
	<a href="${nextLink}">Next</a>	
</div>

<div id="dayPicker" class="span-6">
</div>

<div id="appointmentCalendar" class="span-18 last">
	<table>
		<thead>
			<tr>
				<th>&nbsp;</th>
				<c:forEach var="doctor" items="${appointmentCalendar.doctors}">
					<th class="doctor" data-doctorId="<c:out value="${doctor.id}" />">Dr. <c:out value="${doctor.label}" /></th>
				</c:forEach>
			</tr>
		</thead>	
		<c:forEach var="block" items="${appointmentCalendar.blocks}" varStatus="i">
			<spring:eval expression="appointmentCalendar.getBlockMillis(block)" var="dateTime"/>
			<tr class="${i.index % 2 == 0 ? 'even' : ''}">
				<td class="block">
					<joda:format value="${block}" style="-S" />
				</td>
				<c:forEach var="doctor" items="${appointmentCalendar.doctors}" varStatus="j">
					<spring:eval expression="appointmentCalendar.appointments[i.count - 1][j.count - 1]" var="appointment" />
					<c:choose>
						<c:when test="${appointment != null}">
							<td class="filled" data-id="<c:out value="${appointment.id}"/>" data-dateTime="<c:out value="${dateTime}" />" data-doctorId="<c:out value="${doctor.id}" />">
								<div class="patient">
									<c:out value="${appointment.patient}" />
								</div>
								<div class="client">
									<c:out value="${appointment.client} ${appointment.clientPhone}" />								
								</div>
								<div class="reason">
									<c:out value="${appointment.reason}" />
								</div>
							</td>				
						</c:when>
						<c:otherwise>
							<td class="open" data-dateTime="<c:out value="${dateTime}" />" data-doctorId="<c:out value="${doctor.id}" />">
								&nbsp;
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="addDialog" title="Add Appointment">
	<form id="addForm" action="<c:url value="/appointments" />" method="POST">
		<fieldset>
			<p>
				When: <span id="when"></span>
			</p>
			<p>		
				<label for="patientField">Patient</label><br/>
				<input id="patientField" type="text" />
			</p>
			<p>
				<input id="addButton" type="submit" value="Add" />
			</p>
			<input type="hidden" name="patientId" />
			<input type="hidden" name="doctorId" />
			<input type="hidden" name="dateTime" />
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
				window.location = "${pageContext.request.contextPath}/appointments?day=" + encodeURIComponent(dateText);
			}
		});

		var showAddDialog = function() {
			$("#when").html($(this).parent().children("td.block").html());
			$("#addForm input[name=dateTime]").val($(this).attr("data-dateTime"));
			$("#addForm input[name=doctorId]").val($(this).attr("data-doctorId"));
			$("#addButton").attr("disabled", true);
			$("#addDialog").dialog("open");
			$("#patientField").focus();
		};
		
		var showUpdateDialog = function() {
			var updateDialog = $("#updateDialog");
			updateDialog.dialog('option', 'title', $(this).parent().children("td.block").html());
			updateDialog.children(".patient").html($(this).children(".patient").html());
			updateDialog.attr("data-id", $(this).attr("data-id"));
			updateDialog.dialog("open");
		};
		
		$("#appointmentCalendar td.open").click(showAddDialog);
		$("#appointmentCalendar td.filled").click(showUpdateDialog);
		
		$("#addDialog").dialog({
			autoOpen: false,
			modal: true,
			close: function(event, ui) {
				$("#addForm")[0].reset();
			}			
		});
					
		$("#patientField").autocomplete({
			source: function(request, response) {
				$.getJSON("${pageContext.request.contextPath}/clients/patients", { name: request.term }, response);
			},
			select: function(event, ui) {
				$("#addForm input[name=patientId]").val(ui.item.id);
				$("#addForm input[type=submit]").attr("disabled", false);
				$("#addButton").focus();	
			}
		});

		$("#addButton").click(function() {
			$.post("${pageContext.request.contextPath}/appointments", $("#addForm").serialize(), function(data) {
				$("#addDialog").dialog('close');
			});	
			return false;
		});

		$("#updateDialog").dialog({
			autoOpen: false,
			modal: true
		});
		
		$("#updateDialog .link").click(function() {
			$.ajax({
				url: "${pageContext.request.contextPath}/appointments/" + encodeURIComponent($("#updateDialog").attr("data-id")),
				type: "DELETE",
				success: function(data) {
					$("#updateDialog").dialog('close');
				}
			});			
		});

		setTimeout(function(){

			<c:url var="messagesLink" value="/appointments/messages">
				<c:param name="day" value="${appointmentCalendar.day}" />
			</c:url>

			$.getJSON("${messagesLink}", function(messages) {
				var i, message, slot;
				for (i = 0; i < messages.length; i += 1) {
					message = messages[i];
					if (message.type == "APPOINTMENT_ADDED") {
						slot = $("#appointmentCalendar td.open[data-dateTime=" + message.appointment.dateTime + "][data-doctorId=" + message.appointment.doctorId + "]");
						slot.attr("data-id", message.appointment.id);
						slot.html("<div class='patient'></div><div class='client'></div><div class='reason'></div>");
						slot.children(".patient").html(message.appointment.patient);
						slot.children(".client").html(message.appointment.client + " " + message.appointment.clientPhone);
						slot.children(".reason").html(message.appointment.reason);
						slot.removeClass("open");
						slot.addClass("filled");
						slot.unbind('click').click(showUpdateDialog);						
					} else if (message.type == "APPOINTMENT_DELETED") {
						slot = $("#appointmentCalendar td.filled[data-id=" + message.appointment.id + "]");
						slot.html("&nbsp;");
						slot.removeClass("filled");
						slot.addClass("open");
						slot.unbind('click').click(showAddDialog);
					}
				}
			});
			setTimeout(arguments.callee, 3000);			
		}, 3000);
		
	});
	
</script>