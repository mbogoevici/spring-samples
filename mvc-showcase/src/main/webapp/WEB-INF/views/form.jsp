<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="forms">
	<h2>Forms</h2>
	<p>
		See the <code>org.springframework.samples.mvc.form</code> package for the @Controller code	
	</p>
	<form:form id="form" method="post" modelAttribute="formBean">
		<div class="header">
	  		<h2>Form</h2>
	  		<c:if test="${not empty message}">
				<div class="${message.type}">${message.text}</div>	
	  		</c:if>
	  		<s:bind path="*">
	  			<c:if test="${status.error}">
			  		<div class="error">Form has errors</div>
	  			</c:if>
	  		</s:bind>
		</div>
	  	<fieldset>
	  		<legend>Personal Info</legend>
	  		<form:label path="name">
	  			Name <form:errors path="name" cssClass="error" />
	 		</form:label>
	  		<form:input path="name" />

	  		<form:label path="age">
	  			Age <form:errors path="age" cssClass="error" />
	 		</form:label>
	  		<form:input path="age" />
	  		
	  		<form:label path="birthDate">
	  			Birth Date (in form yyyy-mm-dd) <form:errors path="birthDate" cssClass="error" />
	 		</form:label>
	  		<form:input path="birthDate" />
	  		 
	  		<form:label path="phone">
	  			Phone (in form (###) ###-####) <form:errors path="phone" cssClass="error" />
	  		</form:label>
	  		<form:input path="phone" />

	  		<form:label path="currency">
	  			Currency (in form $#.##) <form:errors path="currency" cssClass="error" />
	  		</form:label>
	  		<form:input path="currency" />

	  		<form:label path="percent">
	  			Percentage (in form ##%) <form:errors path="percent" cssClass="error" />
	  		</form:label>
	  		<form:input path="percent" />

	  	</fieldset>

		<fieldset>
			<legend>Inquiry</legend>
			<form:label path="inquiry">
				Type (select one)
			</form:label>
			<form:select path="inquiry">
				<form:option value="comment">Comment</form:option>
				<form:option value="feedback">Feedback</form:option>
				<form:option value="suggestion">Suggestion</form:option>
			</form:select>
			
	  		<form:label path="inquiryDetails">
	  			Details
	  		</form:label>
	  		<form:textarea path="inquiryDetails" />
	  	</fieldset>

		<fieldset class="checkbox">
			<legend>Request Additional Info</legend>
			<label><form:checkbox path="additionalInfo[mvc]" value="true" />on Spring MVC</label>
			<label><form:checkbox path="additionalInfo[java]" value="true" />on Java (4-ever)</label>				
		</fieldset>
	  		  	
		<fieldset class="radio">
			<legend>Subscribe to Newsletter?</legend>
			<label><form:radiobutton path="subscribeNewsletter" value="true" />Yes</label>
			<label><form:radiobutton path="subscribeNewsletter" value="false" /> No</label>
		</fieldset>

		<input type="submit" value="Submit">
	</form:form>	
</div>