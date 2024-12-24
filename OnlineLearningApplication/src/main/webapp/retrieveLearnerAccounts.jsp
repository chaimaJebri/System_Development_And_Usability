<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Learner Account Directory</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/retrieveLearnerAccounts.css" rel="stylesheet"/> <!-- My css file -->

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
	
	<form class="navbarComponent">
		<input type="text" id="searchForLearnerByUsername" placeholder="Search Learners By Username &#128269; ...">
	</form> 
	
	<form class="navbarComponent">
		<input type="hidden">
	</form>	
</div>
	
	<h1>Learner Account Directory</h1>
	
	<form id="learnersTableForm">
	
		<button type="submit" id="sendEmailBtn" class="notifyBtn btn btn-dark"><i class="bi bi-envelope-at"></i> Notify Learners By Email</button>
		
		<div class="tab-container">
			<table>
				<thead>
					<tr>
						<th><input type="checkbox" id="select-all-learners"></th>
						<th>User ID</th>
						<th>Full Name</th>
						<th>Username</th>
						<th>Email Address</th>
						<th>Interests</th>
						<th>Delete Learner Account</th>
					</tr>
				</thead>
				<tbody id="learnersTableBodyContainer">
					<c:forEach var="learner" items="${learnerAccounts}">
						<tr>
							<td><input type="checkbox" class="a-learner" name="learnerEmails" value="${learner.emailAddress}"></td>
							<td>${learner.userID}</td>
							<td>${learner.fullName}</td>
							<td>${learner.username}</td>
							<td>${learner.emailAddress}</td>
							<td>${learner.interests}</td>
							<td><button title="Delete Learner's Account" class="deleteLearnerAccountBtn btn btn-outline-danger" value="${learner.userID}"><i class="bi bi-trash3-fill"></i></button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</form>

	
	<!-- Notify Learners Modal -->
            <div class="modal fade" id="notifyLearnersModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Notify Learners</h4>
							</div>
							<form id="notifyLearnersForm">
								<div class="modal-body">
										<input type="text" id="emailSubject" name="emailSubject" placeholder="Enter Email Subject">
										<span id="emailSubjectError" class="text-danger"></span>
										
										<input type="text" id="emailText" name="emailText" placeholder="Enter Email Text">
										<span id="emailTextError" class="text-danger"></span>
								</div>
								<div class="modal-footer">
									<button type="button" id="sendEmailBtnModal" class="sendBtn btn btn-success">Send Email</button>
									<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	
	
	
	<!-- Delete Learner Account Modal -->	
			 <div class="modal fade" id="deleteLearnerAccountMdl" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Delete Learner Account</h4>
							</div>
							<div class="modal-body">
								<p>Are you sure you want to delete this account permanently?</p>
							</div>
							<div class="modal-footer">
								<button type="button" id="deleteLearnerAccountBtnMdl" class="btn btn-danger">Delete Account</button>
								<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
							</div>
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

<input type="hidden" id="userIDToSearchLearnerByUsername" value="${userID}">

</body>
</html>