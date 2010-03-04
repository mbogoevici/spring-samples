<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty signinErrorMessage}">
	<div class="error">
		Unable to log in with your OpenID provider:<br/>	
		<c:out value="${signinErrorMessage}"/>
	</div>
</c:if>

<form action="<c:url value="/users/signin/authenticate" />" method="post">
  	<fieldset>		
		<p>
			<label for="openid_identifier">Enter your OpenID URL:</label><br/>
			<input id="openid_identifier" name="openid_identifier" />			
		</p>
		<p>	
			<input type="submit" value="Sign In"/>
		</p>
	</fieldset>
</form>