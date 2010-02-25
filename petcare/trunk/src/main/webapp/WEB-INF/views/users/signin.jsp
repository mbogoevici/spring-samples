<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="<c:url value="/users/signin/openid"/>" method="post">
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