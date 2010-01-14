<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>Search Clients</h2>

<form:form id="searchForm" action="clients/search" modelAttribute="clientSearchForm" method="get">

	<form:label for="lastName" path="lastName">
		Last Name <form:input path="lastName" />
	</form:label>

	<input type="submit" value="Search" />	

</form:form>