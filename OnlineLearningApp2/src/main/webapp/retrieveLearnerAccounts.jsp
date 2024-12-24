<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>LA Directory</title>
	
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
		<button type="submit" class="btn btn-dark">Main</button>
	</form>
</div>
	
	<h1>LA Directory</h1>
	
	<form id="learnersTableForm">
	
		<button type="submit" id="sendEmailBtn" class="notifyBtn btn btn-dark">Inform</button>
		
		<div class="tab-container">
			<table>
				<thead>
					<tr>
						<th>-</th>
						<th>Identifier</th>
						<th>Name</th>
						<th>Alias</th>
						<th>E-Address</th>
						<th>Hobbies</th>
						<th>Remove</th>
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
							<td><button class="deleteLearnerAccountBtn btn btn-outline-primary" value="${learner.userID}">Remove</button></td>
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
								<h4>Inform</h4>
							</div>
							<form id="notifyLearnersForm">
								<div class="modal-body">
										<input type="text" id="emailSubject" name="emailSubject" placeholder="Subject">
										
										<input type="text" id="emailText" name="emailText" placeholder="Text">
								</div>
								<div class="modal-footer">
									<button type="button" id="sendEmailBtnModal" class="sendBtn btn btn-success">Deliver</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

<input type="hidden" id="userIDToSearchLearnerByUsername" value="${userID}">

</body>
</html>