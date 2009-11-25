<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Appointment Calendar</h2>

<c:forEach var="entry" items="${doctorAppointmentsMap}">
	<h3>Dr. ${entry.key}</h3>
	<c:forEach var="appointment" items="${entry.value}">
		<p>
			${appointment.dateTime} ${appointment.patient} <br/>
			${appointment.client} ${appointment.clientPhone} <br/>
			${appointment.notes}
		</p>		
	</c:forEach>
</c:forEach>