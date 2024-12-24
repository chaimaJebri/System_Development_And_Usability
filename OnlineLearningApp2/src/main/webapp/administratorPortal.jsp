<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Welcome</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/administratorPortal.css" rel="stylesheet"/> <!-- My CSS file -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/adminScript.js" type="text/javascript"></script> <!-- My Script file -->
	
</head>
<body>

	<!-- Drop Down Menu -->
	<div class="dropdown">
	  <button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
	    <i class="bi bi-pc-display-horizontal"></i>
	  </button>
	  <ul class="dropdown-menu">
	    <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#editProfileModal">Modify Details</a></li>
	    <li><hr class="dropdown-divider"></li>
	    <li><a class="dropdown-item" href="#" id="deleteAccountButtonModal">Remove</a></li>
	  </ul>
	</div>
	
	
	<form class="adminPortalButtons" method="get" action="./RetrieveLearnerAccounts">
 	    <input type="hidden" name="userID" value="${user.userID}">
    	<button type="submit">Manage LAs</button>
    </form>
    
	<h1>Welcome to your portal, the main place to manage everything in the system.</h1>
	<h2>Please take a moment to go through the options below. Each one is important for making sure the system runs smoothly and efficiently. By checking out and using these options, you will help keep everything working at its best.</h2>
	
	<form class="adminPortalButtons" method="get" action="./RetrieveCourses">
		<input type="hidden" name="userID" value="${user.userID}">
    	<button type="submit">Manage Course Materials</button>
    </form>
    
    <form class="adminPortalButtons">
    	<button type="submit" id="confirmLogout" >Leave</button>
    </form>
    
    <form class="adminPortalButtons" method="get" action="./TermsAndPoliciesHandler">
 	    <input type="hidden" name="userID" value="${user.userID}">
    	<button type="submit">Modify What users need to adhere to.</button>
    </form>
    
 
			
<!-- Edit Profile Details Modal -->
            <div class="modal fade" id="editProfileModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Modify Details</h4> 
							</div>
							<form id="editProfileForm">
								<div class="modal-body">
										
										<input type="hidden" id="userIDToEditAccount" name="userIDToEditAccount" value="${user.userID}">
										
										<input type="text" id="editedInterests" name="editedInterests" placeholder="Hobbies">
										
										<input type="text" id="editedUsername" name="editedUsername" placeholder="Alias" value="${user.username}" readonly>
										
										<input type="text" id="editedEmailAddress" name="editedEmailAddress" placeholder="E-Address">
										
										<input type="password" id="currentPassword" name="currentPassword" placeholder="Code 01">
										
										<input type="password" id="editedConfirmPassword" name="editedConfirmPassword" placeholder="Confirm Code 02">
										
										<input type="password" id="editedPassword" name="editedPassword" placeholder="Code 02" title="Code needs to be strong">
										
										<input type="text" id="editedFullName" name="editedFullName" placeholder="Name">
										
								</div>
								<div class="modal-footer">
									<button type="button" id="editProfileButtonModal" class="saveBtn btn btn-success">Save</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

<input type="hidden" id="userIDToDeleteAccount" value="${user.userID}">

</body>
</html>