<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h2>Add New Appointment</h2>

<form:form id="addNewForm" action="${pageContext.request.contextPath}/appointments" modelAttribute="appointmentForm" method="post">
	<fieldset>
		
		<form:label	for="doctor" path="doctor">
			Doctor <form:input path="doctor" />			
		</form:label> 
		
		<form:label for="client" path="client">
			Client <form:input path="client" />
		</form:label> 
		
		<form:label for="patient" path="patient">
			Patient <form:input path="patient" />
		</form:label>
		
		<form:label for="date" path="date">
			Date <form:input path="date" /> <form:errors path="date" />
		</form:label>
		
		<form:label for="time" path="time">
			Time <form:input path="time" /> <form:errors path="time" />
		</form:label>
		
		<form:label for="notes" path="notes">
			Notes <form:input path="notes" /> 
		</form:label>
		
		<input type="submit" value="Add" />
		
	</fieldset>
	
</form:form>