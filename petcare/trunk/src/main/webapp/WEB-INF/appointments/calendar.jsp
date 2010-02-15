<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<h2>Appointment Calendar for <spring:eval expression="appointmentCalendar.day" /></h2>

<table>
	<tr>
		<th>&nbsp;</th>
		<c:forEach var="doctor" items="${appointmentCalendar.doctors}">
			<th>Dr. ${doctor}</th>
		</c:forEach>
	</tr>

	<c:forEach var="block" items="${appointmentCalendar.blocks}">
		<tr>
			<td><spring:eval expression="block" /></td>
			<c:forEach var="doctor" items="${appointmentCalendar.doctors}">
				<td>
					<spring:eval expression="appointmentCalendar.appointmentFor(doctor, block)" var="appointment" />
					<c:if test="${appointment}">
						${appointment.dateTime} ${appointment.patient} <br/>
						${appointment.client} ${appointment.clientPhone} <br/>
						${appointment.reason}			
					</c:if>
				</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>
<ul>
<li><a href="<c:url value="/appointments/${appointmentCalendar.previousDayResourceId}"/>">Previous</a></li> 
<li><a href="<c:url value="/appointments/${appointmentCalendar.nextDayResourceId}"/>">Next</a></li>
</ul>