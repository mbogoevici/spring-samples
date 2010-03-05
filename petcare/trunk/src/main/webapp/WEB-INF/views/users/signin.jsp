<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty signinErrorMessage}">
	<div class="error">
		Unable to log in with your OpenID provider:<br/>	
		<c:out value="${signinErrorMessage}"/>
	</div>
</c:if>

<h2>Sign in with OpenID</h2>

<form id="openid_form" action="<c:url value="/users/signin/authenticate" />" method="post">
  	<fieldset>	
  		<div id="openid_choice">
	  		<label for="openid_btns">Please select your account provider:</label>
	   		<div id="openid_btns"></div>
  		</div>
        <div id="openid_input_area">
        </div>
		<p>
			<label for="openid_identifier">Or manually enter your OpenID URL:</label><br/>
			<input id="openid_identifier" name="openid_identifier" class="openid-identifier" />			
			<input id="openid_submit" type="submit" value="Sign In"/>
		</p>
	</fieldset>
</form>

<script type="text/javascript">
	$(document).ready(function() {
		openid.init("openid_identifier");
        $("#openid_identifier").focus();
	});
</script>