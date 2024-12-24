<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit FAQs</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/termsPolicies-FAQs.css" rel="stylesheet"/> <!-- My css file -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/adminScript.js" type="text/javascript"></script> <!-- My Script file -->
</head>
<body>

	<div class="navbarContainer">
		<form method="post" action="./AdministratorPortal" class="navbarComponent">
			<input type="hidden" name="userID" value="${userID}">
			<button type="submit" title="Back to administrator portal" class="btn btn-dark"><i class="bi bi-house"></i> Home</button>
		</form>
	</div>

	<h1>FAQs</h1>
	
	<div class="formContainer">
		<form method="post" action="./FAQsHandler">
			
			<textarea id="editedFAQs" class="formComponent">${FAQs}</textarea>
			<span id="validateEditedFAQs" class="formComponent"></span>
			
			<button type="submit" id="editFAQsBtn" class="formComponent btn btn-success">Save Changes</button>
		
		</form>
	</div>
	
	
	
<!-- Toast Notifications Container -->
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="toastNotifications" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <strong class="me-auto">Notification</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
            <!-- Toast message will be inserted here -->
        </div>
    </div>
</div>

</body>
</html>