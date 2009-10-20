<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h2>Add New Appointment</h2>

<form:form id="addNewForm"
	action="${pageContext.request.contextPath}/appointments"
	modelAttribute="appointmentForm" method="post">
	<fieldset>
		<div class="field">
			<form:label	for="doctor" path="doctor" cssClass="label">
				Doctor <form:input path="doctor" cssClass="input"/>
			</form:label> 
		</div>
		
		<div class="field">
			<form:label for="client" path="client" cssClass="label">
				Client <form:input path="client" cssClass="input"/>
			</form:label> 
		</div>
		
		<div class="field">
			<form:label for="patient" path="patient" cssClass="label">
				Patient <form:input path="patient" cssClass="input"/>
			</form:label>
		</div> 
		
		<div class="field">
			<form:label for="date" path="date" cssClass="label">
				Date <form:input path="date" cssClass="input"/>
			</form:label>
		</div> 
		
		<div class="field">
			<form:label for="time" path="time" cssClass="label">
				Time <form:input path="time" cssClass="input"/>
			</form:label>
		</div> 
		
		<div class="field">
			<form:label for="notes" path="notes" cssClass="label">
				Notes <form:input path="notes" cssClass="input"/>
			</form:label>
		</div>
		
		<input type="submit" value="Add" />
	</fieldset>
	
</form:form>