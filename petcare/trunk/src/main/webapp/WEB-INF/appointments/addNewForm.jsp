<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h2>Add New Appointment</h2>

<form:form id="addNewForm" action="${pageContext.request.contextPath}/appointments" modelAttribute="appointmentForm" method="post">
  	<fieldset>		
		<legend>Appointment Fields</legend>
		<p>
			<form:label	for="doctor" path="doctor" cssErrorClass="error">Doctor</form:label><br/>
			<form:input path="doctor" /> <form:errors path="doctor" />			
		</p>
		<p>	
			<form:label for="client" path="client" cssErrorClass="error">Client</form:label><br/>
			<form:input path="client" /> <form:errors path="client" />
		</p>
		<p>
			<form:label for="patient" path="patient" cssErrorClass="error">Patient</form:label><br/>
			<form:input path="patient" /> <form:errors path="patient" />
		</p>
		<p>	
			<form:label for="date" path="date" cssErrorClass="error">Date</form:label><br/>
			<form:input path="date" /> <form:errors path="date" />
		</p>
		<p>	
			<form:label for="time" path="time" cssErrorClass="error">Time</form:label><br/>
			<form:input path="time" /> <form:errors path="time" />
		</p>
		<p>	
			<form:label for="notes" path="notes" cssErrorClass="error">Notes</form:label><br/>
			<form:input path="notes" /> <form:errors path="notes" />
		</p>
		<p>	
			<input type="submit" />
		</p>
	</fieldset>
	
</form:form>