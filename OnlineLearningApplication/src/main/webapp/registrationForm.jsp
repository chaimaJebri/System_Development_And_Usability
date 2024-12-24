<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Sign Up</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link href="./css/registration-login.css" rel="stylesheet"/> <!-- My css file -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/registration-login.js" type="text/javascript"></script> <!-- My Script file -->

</head>
<body>

<div>

	<h1>Sign Up</h1>
	<p>Please fill in this form to create an account</p>
	
	<form>
		
		<input type="text" id="fullName" name="fullName" placeholder="Enter Full Name">
		<span id="validateFullName"></span>
		
		<input type="text" id="username" name="username" placeholder="Enter Username" title="Choose a username with at least 5 characters.">
		<span id="validateUsername"></span>
		
		<input type="text" id="emailAddress" name="emailAddress" placeholder="Enter Email Address">
		<span id="validateEmailAddress"></span>
		
		<input type="password" id="password" name="password" placeholder="Enter Password" title="Use a Strong Password: Minimum 8 characters including letters (abcABC), numbers (123), and symbols (+/$£&).">
		<span id="validatePassword"></span>
		
		<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
		<span id="validateConfirmPassword"></span>
		
		<input type="text" id="interests" name="interests" placeholder="Enter Interests (Optional)">
		
		<input type="hidden" id="role" name="role" value="learner">
		
		<span id="isCreated"></span>
		<button type="submit" id="signUpButton">Sign Up</button>
		
	</form>
	
	<a href="loginForm.jsp" class="loginLink">Login</a>
	
	<p>By signing up you agree to our <a href="./TermsAndPoliciesHandler?userID=0">Terms and Policies</a></p>

</div>

</body>
</html>