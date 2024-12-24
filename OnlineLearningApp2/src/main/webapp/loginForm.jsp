<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Access</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link href="./css/registration-login.css" rel="stylesheet"/> <!-- My css file -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/registration-login.js" type="text/javascript"></script> <!-- My Script file -->
	
</head>
<body>

<div>
	<h1>Access</h1>
	<p>Fill the form.</p>
	
	<form action="./UserLogin" method="post">
	
		<button type="submit" id="loginButton">Access</button>
	
		<input type="text" id="usernameLogin" name="usernameLogin" placeholder="Alias" required>
		
		<input type="password" id="passwordLogin" name="passwordLogin" placeholder="Code" required>
		
		
		<c:if test="${not empty errorMessage}"> <p class="authenticationFailed">Error..</p></c:if>
		
	</form>

	<br>
	<a href="registrationForm.jsp" id="registerLink">Join Now!</a>
	<br>
</div>

</body>
</html>