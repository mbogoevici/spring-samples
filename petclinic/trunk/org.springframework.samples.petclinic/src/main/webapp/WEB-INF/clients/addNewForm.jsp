<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2>Add New Client</h2>

<form:form id="addNewForm" action="${pageContext.request.contextPath}/clients" modelAttribute="ownerForm" method="post">
	<form:label for="firstName" path="firstName">
		First Name <form:input path="firstName" />
	</form:label>
	<form:label for="lastName" path="lastName">
		Last Name <form:input path="lastName" />
	</form:label>
	<form:label for="phone" path="phone">
		Phone <form:input path="phone" />
	</form:label>	
	<form:label for="address.street" path="address.street">
		Street <form:input path="address.street" />
	</form:label>
	<form:label for="address.apartment" path="address.apartment">
		Apartment <form:input path="address" />
	</form:label>
	<form:label for="address.city" path="address.city">
		City <form:input path="address.city" />
	</form:label>
	<form:label for="address.state" path="address.state">
		State <form:input path="address.state" />
	</form:label>
	<form:label for="address.zip" path="address.zip">
		Zip <form:input path="address.zip" />
	</form:label>
	<form:label for="address.country" path="address.country">
		Country <form:input path="address.country" />
	</form:label>
	<input type="submit" value="Add" />	
</form:form>