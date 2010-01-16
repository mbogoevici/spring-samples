<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Appointment Calendar</h2>

<p>${appointmentCalendar.day}</p>

<c:if test="${appointmentCalendar.hasAppointments}">
	<c:forEach var="entry" items="${appointmentCalendar.doctorAppointments}">
		<h3>Dr. ${entry.key}</h3>
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
	<p>No appointments</p>
</c:if>
<ul>
<li><a href="<c:url value="/appointments/${appointmentCalendar.previousDayResourceId}"/>">Previous</a></li> 
<li><a href="<c:url value="/appointments/${appointmentCalendar.nextDayResourceId}"/>">Next</a></li>
</ul>