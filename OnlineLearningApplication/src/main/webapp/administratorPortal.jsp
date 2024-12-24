<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Administrator Portal</title>
	
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
	  <button class="dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" title="Your Profile">
	    <i class="bi bi-person-circle"></i>
	  </button>
	  <ul class="dropdown-menu">
	    <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#editProfileModal"><i class="bi bi-pen"></i> Edit Profile Details</a></li>
	    <li><hr class="dropdown-divider"></li>
	    <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#logoutModal"><i class="bi bi-box-arrow-right"></i> Logout</a></li>
	    <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#deleteAccountModal"><i class="bi bi-trash"></i> Delete Account</a></li>
	  </ul>
	</div>

	<h1>Glad to have you back Administrator ${user.fullName}.</h1>
	<h2>In your portal, feel free to choose from the following options</h2>
	
	<form class="adminPortalButtons" method="get" action="./RetrieveCourses">
		<input type="hidden" name="userID" value="${user.userID}">
    	<button type="submit">Manage Courses, Modules & Quizzes</button>
    </form>
    
    <form class="adminPortalButtons" method="get" action="./RetrieveLearnerAccounts">
 	    <input type="hidden" name="userID" value="${user.userID}">
    	<button type="submit">Manage Learner Accounts</button>
    </form>
    
    <form class="adminPortalButtons" method="get" action="./TermsAndPoliciesHandler">
 	    <input type="hidden" name="userID" value="${user.userID}">
    	<button type="submit">Edit System's Terms & Policies</button>
    </form>
    
    <form class="adminPortalButtons" method="get" action="./FAQsHandler">
 	    <input type="hidden" name="userID" value="${user.userID}">
    	<button type="submit">Edit FAQs (Frequently Asked Questions)</button>
    </form>
    
    
<!-- Delete account modal -->	
			 <div class="modal fade" id="deleteAccountModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Delete Account</h4>
							</div>
							<div class="modal-body">
								<p>This action will permanently delete your account. Are you sure you want to proceed?</p>
								<input type="hidden" id="userIDToDeleteAccount" value="${user.userID}">
							</div>
							<div class="modal-footer">
								<button type="button" id="deleteAccountButtonModal" class="btn btn-danger">Delete Account</button>
								<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
							</div>
						</div>
					</div>
				</div>
			</div>    
			
    
<!-- Logout Modal -->	
			 <div class="modal fade" id="logoutModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Logging Out</h4>
							</div>
							<div class="modal-body">
								<p>Are you sure you want to log out?</p>
							</div>
							<div class="modal-footer">
								<button type="button" id="confirmLogout" class="btn btn-danger">Confirm</button>
								<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
<!-- Edit Profile Details Modal -->
            <div class="modal fade" id="editProfileModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Edit Profile Details</h4> 
							</div>
							<form id="editProfileForm">
								<div class="modal-body">
										<p><strong>Important: </strong>If no new password is provided, your current password will be retained.</p>
										
										<input type="hidden" id="userIDToEditAccount" name="userIDToEditAccount" value="${user.userID}">
										
										<input type="text" id="editedFullName" name="editedFullName" placeholder="Enter Full Name" value="${user.fullName}">
										<span id="validateEditedFullName"></span>
										
										<input type="text" id="editedUsername" name="editedUsername" placeholder="Enter Username" value="${user.username}" readonly>
										
										<input type="text" id="editedEmailAddress" name="editedEmailAddress" placeholder="Enter Email Address" value="${user.emailAddress}">
										<span id="validateEditedEmailAddress"></span>
										
										<input type="password" id="currentPassword" name="currentPassword" placeholder="Enter Current Password">
										<span id="validateCurrentPassword"></span>
										
										<input type="password" id="editedPassword" name="editedPassword" placeholder="Enter New Password" title="Use a Strong Password: Minimum 8 characters including letters (abcABC), numbers (123), and symbols (+/$£&).">
										<span id="validateEditedPassword"></span>
										
										<input type="password" id="editedConfirmPassword" name="editedConfirmPassword" placeholder="Confirm New Password">
										<span id="validateEditedConfirmPassword"></span>
										
										<input type="text" id="editedInterests" name="editedInterests" placeholder="Enter Interests (Optional)" value="${user.interests}">
								</div>
								<div class="modal-footer">
									<button type="button" id="editProfileButtonModal" class="saveBtn btn btn-success">Save Changes</button>
									<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
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