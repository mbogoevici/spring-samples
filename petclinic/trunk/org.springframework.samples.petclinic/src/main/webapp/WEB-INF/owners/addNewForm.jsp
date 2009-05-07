<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2>Add New Owner</h2>

<form:form id="addNewForm" action="${pageContext.request.contextPath}/owners" modelAttribute="ownerForm" method="post">
	<form:label for="firstName" path="firstName">
		First Name <form:input path="firstName" />
	</form:label>
	<form:label for="lastName" path="lastName">
		Last Name <form:input path="lastName" />
	</form:label>
	<form:label for="address" path="address">
		Address <form:input path="address" />
	</form:label>
	<form:label for="city" path="city">
		City <form:input path="city" />
	</form:label>
	<form:label for="state" path="state">
		State <form:input path="state" />
	</form:label>
	<form:label for="zip" path="zip">
		Zip <form:input path="zip" />
	</form:label>
	<form:label for="country" path="country">
		Country <form:input path="country" />
	</form:label>
	<form:label for="phone" path="phone">
		Phone <form:input path="phone" />
	</form:label>
	<input type="submit" value="Add" />	
</form:form>