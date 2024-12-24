<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Register</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link href="./css/registration-login.css" rel="stylesheet"/> <!-- My css file -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/registration-login.js" type="text/javascript"></script> <!-- My Script file -->

</head>
<body>

<div>

	<h1>Join</h1>
	<p>Fill the form.</p>
	<a href="loginForm.jsp" class="loginLink">Next</a>
	
	<form>
		
		<input type="text" id="fullName" name="fullName" placeholder="Name">
		
		<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Code">
		
		<input type="text" id="interests" name="interests" placeholder="Hobbies">
		
		<input type="text" id="emailAddress" name="emailAddress" placeholder="E-Address">
		
		<input type="password" id="password" name="password" placeholder="Code" title="Code needs to be strong">
		
		<input type="text" id="username" name="username" placeholder="Alias" title="Alias needs to be valid.">
		
		<input type="hidden" id="role" name="role" value="learner">
		
		<button type="submit" id="signUpButton">Join</button>
		
	</form>
	
	<p><a href="./TermsAndPoliciesHandler?userID=0">Rules</a></p>

</div>

</body>
</html>