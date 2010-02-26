<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div id="dayPicker" class="span-6 prepend-top">
</div>

<div id="appointmentCalendar" class="span-18 last">
	<div id="appointmentDay" class="span-4">
		<spring:eval expression="appointmentCalendar.day" />
	</div>
	<div id="appointmentNavigation" class="span-4 append-10 last">
		<a href="<c:url value="/appointments?day=${appointmentCalendar.previousDay}"/>">Previous</a> 
		<a href="<c:url value="/appointments?day=${appointmentCalendar.nextDay}"/>">Next</a>	
	</div>
	<table>
		<thead>
			<tr>
				<th>&nbsp;</th>
				<c:forEach var="doctor" items="${appointmentCalendar.doctors}">
					<th class="doctor" data-doctorId="${doctor.id}">Dr. ${doctor.label}</th>
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
							<td class="filled" data-id="${appointment.id}" data-dateTime="${dateTime}" data-doctorId="${doctor.id}">
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
							<td class="open" data-dateTime="${dateTime}" data-doctorId="${doctor.id}">
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
	<form id="addForm" action="${pageContext.request.contextPath}/appointments" method="POST">
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
				window.location = "${pageContext.request.contextPath}/appointments?day=" + dateText;
			}
		});
		
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
				$.getJSON("${pageContext.request.contextPath}/patients", { name: request.term }, response);
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
				url: "${pageContext.request.contextPath}/appointments/" + $("#updateDialog").attr("data-id"),
				type: "DELETE",
				success: function(data) {
					$("#updateDialog").dialog('close');
					location.reload();	
				}
			});			
		});

		setTimeout(function(){
			$.getJSON("${pageContext.request.contextPath}/appointments/messages?day=${appointmentCalendar.day}", function(messages) {
				var i;
				for (i = 0; i < messages.length; i += 1) {
					var message = messages[i];
					if (message.headers.type == "appointmentAdded") {
						var slot = $("#appointmentCalendar td.open[data-dateTime=" + message.payload.dateTime + "][data-doctorId=" + message.payload.doctorId + "]");
						slot.attr("data-id", message.payload.id);
						slot.html("<div class='patient'></div><div class='client'></div><div class='reason'></div>");
						slot.children(".patient").html(message.payload.patient);
						slot.children(".client").html(message.payload.client + " " + message.payload.clientPhone);
						slot.children(".reason").html(message.payload.reason);
						slot.removeClass("open");
						slot.addClass("filled");
						slot.unbind('click').click(showUpdateDialog);						
					} else if (message.headers.type == "appointmentDeleted") {
						var slot = $("#appointmentCalendar td.filled[data-id=" + message.payload.id + "]");
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

	var showAddDialog = function() {
		$("#when").html($(this).parent().children("td.block").html());
		$("#addForm input[name=dateTime]").val($(this).attr("data-dateTime"));
		$("#addForm input[name=doctorId]").val($(this).attr("data-doctorId"));
		$("#addButton").attr("disabled", true);
		$("#addDialog").dialog("open");
		$("#patientField").focus();
	};
	
	var showUpdateDialog = function() {
		$("#updateDialog").dialog('option', 'title', $(this).parent().children("td.block").html());
		$("#updateDialog .patient").html($(this).children(".patient").html());
		$("#updateDialog").attr("data-id", $(this).attr("data-id"));
		$("#updateDialog").dialog("open");
	};
	
</script>