<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Terms & Policies</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/termsPolicies-FAQs.css" rel="stylesheet"/> <!-- My css file -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
</head>
<body>

	<div class="navbarContainer">
		<form action="./LearnerPortal" method="post" class="navbarComponent">
			<input type="hidden" name="userID" value="${userID}">
			<button type="submit" title="Back to portal"  class="btn btn-dark"><i class="bi bi-house"></i> Home</button>
		</form>
	</div>
	
	<h1>System's Terms & Policies</h1>
	
	<div class="formContainer">
		<form>
			
			<textarea class="formComponent textArea2" readonly>${termsAndPolicies}</textarea>
		
		</form>
	</div>


</body>
</html>