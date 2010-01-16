<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Appointment Calendar</h2>

${appointmentCalendar.day}

<c:if test="${appointmentCalendar.hasAppointments}">
	<c:forEach var="entry" items="${appointmentCalendar.doctorAppointments}">
		<h3>${entry.key}</h3>
		<c:forEach var="appointment" items="${entry.value}">
			<p>
				${appointment.dateTime} ${appointment.patient} <br/>
				${appointment.client} ${appointment.clientPhone} <br/>
				${appointment.notes}
			</p>		
		</c:forEach>
	</c:forEach>
</c:if>
<c:if test="${!appointmentCalendar.hasAppointments}">
	No appointments
</c:if>
<a href="${appointmentCalendar.previousDayResourceId}">Previous</a> 
<a href="${appointmentCalendar.nextDayResourceId}">Next</a>