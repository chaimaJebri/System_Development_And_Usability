<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Course Materials</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/retrieveQuestionsAndOptionsAdministrator.css" rel="stylesheet"/> <!-- My CSS file -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/questionAndOptionScript.js" type="text/javascript"></script> <!-- My Script file -->
</head>
<body>

<c:choose>
	<c:when test="${not empty quizNotFound}">
		<div class="navbarContainer">
			<form action="./AdministratorPortal" method="post" class="navbarComponent">
				<input type="hidden" name="userID" value="${userID}">
				<button type="submit" class="btn btn-dark">Main</button>
			</form>
		</div>
	
		<h1 class="quizNotFoundHeader">${quizNotFound}</h1>
	</c:when>
	
	<c:otherwise>
	
	    <div class="navbarContainer">
		
			<button class="navbarComponent btn btn-dark" data-bs-toggle="modal" data-bs-target="#addQuestionOptionsModal"><i class="bi bi-trash"></i> Create</button>
			
			<form action="./AdministratorPortal" method="post" class="navbarComponent">
				<input type="hidden" name="userID" value="${userID}">
				<button type="submit" class="btn btn-dark">Main</button>
			</form>
			
			<form class="navbarComponent" method="get" action="./RetrieveLearnerAccounts">
 	    		<input type="hidden" name="userID" value="${userID}">
    			<button type="submit" class="btn btn-dark">Manage LAs</button>
    		</form>
			
		</div>
		
		
		<c:choose>
			<c:when test="${not empty noQuestions}">
				<h3>${noQuestions}</h3>
			</c:when>
			
			<c:otherwise>
				<!-- In this group each question will be displayed in an element and in that element there is another group to display the option associated with the question  -->
				<div class="questionsOptsContainer">
					<ul class="list-group">
						<c:forEach var="question" items="${questions}" varStatus="i">
							<li class="list-group-item eachQuestion">
								<p class="questionTextClass">${question.questionText}</p>
								<div class="editDeleteBtns">
									<button class="editQuestionButton btn btn-outline-danger" value="${question.questionID}">Modify</button>
									<button class="deleteQuestionButton btn btn-outline-primary" value="${question.questionID}">Remove</button>
								</div>
								
								<ul class="list-group">
									<c:forEach var="option" items="${options[question.questionID]}">
										<li class="list-group-item eachOption">
											<p>${option.optionText}:
												<c:choose>
													<c:when test="${option.isCorrect}">Right</c:when>
													<c:otherwise>No</c:otherwise>
												</c:choose>
											</p>
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
		
		
		<!-- Add new Question and its associated options modal -->
            <div class="modal fade" id="addQuestionOptionsModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Create</h4>
							</div>
							<form id="addQuestionOptionsForm">
								<div class="modal-body">								
										<input type="hidden" id="quizID-add" name="quizID-add" value="${quiz.quizID}">
										
										<input type="text" id="addedQuestionText" name="addedQuestionText" placeholder="Question Text">
										
										<div class="optionContainer">
											<input type="text" id="addedOptionText1" name="addedOptionText1" placeholder="Option Text">
											<input type="checkbox" id="addedIsCorrect1" name="addedIsCorrect1" value="true">
										</div>
										
										<div class="optionContainer">
											<input type="text" id="addedOptionText2" name="addedOptionText2" placeholder="Option Text">
											<input type="checkbox" id="addedIsCorrect2" name="addedIsCorrect2" value="true">
										</div>
										
										<div class="optionContainer">
											<input type="text" id="addedOptionText3" name="addedOptionText3" placeholder="Option Text">
											<input type="checkbox" id="addedIsCorrect3" name="addedIsCorrect3" value="true">
										</div>
										
										<div class="optionContainer">
											<input type="text" id="addedOptionText4" name="addedOptionText4" placeholder="Option Text">
											<input type="checkbox" id="addedIsCorrect4" name="addedIsCorrect4" value="true">
										</div>
										
										<span id="validateProvidedOptions-add" class="text-danger"></span>
										<span id="validateCorrectOption-add" class="text-danger"></span>
								</div>
								<div class="modal-footer">
									<button type="button" id="addButtonModal" class="addEditBtns btn btn-success">Create</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			
		<!-- Edit Question Modal -->
            <div class="modal fade" id="editQuestionModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Modify</h4>
							</div>
							<form id="editQuestionForm">
								<div class="modal-body">
										<input type="hidden" id="quizID-edit" name="quizID-edit" value="${quiz.quizID}">
										<input type="hidden" id="questionID-edit" name="questionID-edit">
										
										<input type="text" id="editedQuestionText" name="editedQuestionText" placeholder="Question Text">
								</div>
								<div class="modal-footer">
									<button type="button" id="editQuestionButtonModal" class="addEditBtns btn btn-success">Save</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			
	</c:otherwise>
	

</c:choose>




</body>
</html>