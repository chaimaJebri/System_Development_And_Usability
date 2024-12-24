<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Login</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link href="./css/registration-login.css" rel="stylesheet"/> <!-- My css file -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/registration-login.js" type="text/javascript"></script> <!-- My Script file -->
	
</head>
<body>

<div>
	<h1>Login</h1>
	<p>Hey there! Great to see you again</p>
	
	<form action="./UserLogin" method="post">
	
		<input type="text" id="usernameLogin" name="usernameLogin" placeholder="Enter Username" required>
		<span id="validateUsernameLogin"></span>
		
		<input type="password" id="passwordLogin" name="passwordLogin" placeholder="Enter Password" required>
		<span id="validatePasswordLogin"></span>
		
		<p class="pubDevice">When using public or shared devices, remember to sign out to protect your account.</p>
		
		<c:if test="${not empty errorMessage}"> <p class="authenticationFailed">Authentication failed. Please try again.</p></c:if>
		<button type="submit" id="loginButton" >Login</button>
		
	</form>

	<p>New User ?</p>
	<a href="registrationForm.jsp">Create an account Now !</a>
</div>

</body>
</html>