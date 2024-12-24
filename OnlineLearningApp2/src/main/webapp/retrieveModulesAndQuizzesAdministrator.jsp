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
	<link href="./css/retrieveModulesAndQuizzesAdministrator.css" rel="stylesheet"/> <!-- My CSS file -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/moduleAndQuizScript.js" type="text/javascript"></script> <!-- My Script file -->
</head>
<body>

<c:choose>
    
	<c:when test="${not empty courseNotFound}">
	
		<div class="navbarContainer">
			<form action="./RetrieveCourses" method="get" class="navbarComponent">
				<input type="hidden" name="userID" value="${userID}">
				<button type="submit" class="prvBtn btn btn-dark">Previous</button>
			</form>
		</div>
		
		<h1 class="courseNotFoundHeader">${courseNotFound}</h1>
		
	</c:when>
	
	
	<c:otherwise>
	
		<div class="navbarContainer">
		
			<button data-bs-toggle="modal" data-bs-target="#addModuleModal" class="navbarComponent btn btn-dark"><i class="bi bi-trash"></i> Create1</button>
		
			<form class="navbarComponent">
				<input type="text" id="searchNotWorking" placeholder="Type Something ...">
			</form>
			
			<button data-bs-toggle="modal" data-bs-target="#addQuizModal" class="navbarComponent btn btn-dark"><i class="bi bi-trash"></i> Create2</button>
			
		</div>	
	
			
		<h1>Content</h1>	
		
		<!-- Modules Section -->
		
		
		<c:choose>
			<c:when test="${not empty noModules}">
				<h3>${noModules}</h3>
			</c:when>
			
			<c:otherwise>
				<div class="container">
					<div id= "moduleCards" class="row row-cols-1 row-cols-md-3 g-4">
						<c:forEach var="module" items="${courseModules}">
							<div class="col">
								<div class="card module-quiz-card">
						            <div class="card-body">
							            <h5 class="card-title"><a href="./RetrieveModuleContent?moduleID=${module.moduleID}&courseID=${module.courseID}&userID=${userID}">${module.moduleTitle}</a></h5>
							            <p class="card-text">${module.moduleOverview}</p>
							            <div class="deleteEditButtons">
								            <button class="editModuleButton btn btn-outline-danger" value="${module.moduleID}" data-moduleContent="${module.moduleContent}">Modify</button>
								            <button class="deleteModuleButton btn btn-outline-primary" value="${module.moduleID}">Remove</button>
						           		</div>
						            </div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</c:otherwise>	
		</c:choose>
		
		<!-- Quizzes Section -->
		
		<c:choose>
			<c:when test="${not empty noQuizzes}">
				<h3>${noQuizzes}</h3>
			</c:when>
			
			<c:otherwise>
				<div class="container">
					<div id= "quizCards" class="row row-cols-1 row-cols-md-3 g-4">
						<c:forEach var="quiz" items="${courseQuizzes}">
							<div class="col">
								<div class="card module-quiz-card">
						            <div class="card-body">
							            <h5 class="card-title"><a href="./RetrieveQuestionsAndOptions?quizID=${quiz.quizID}&courseID=${course.courseID}&userID=${userID}">${quiz.quizName}</a></h5>
							            <p class="card-text">${quiz.quizOverview}</p>
							            <div class="deleteEditButtons">
								            <button class="editQuizButton btn btn-outline-danger" value="${quiz.quizID}">Modify</button>
								            <button class="deleteQuizButton btn btn-outline-primary" value="${quiz.quizID}">Remove</button>
						            	</div>
						            </div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</c:otherwise>	
		</c:choose>
		
		
<input type="hidden" id="userID" value="${userID}">
		
<!-- Add new Module modal -->
            <div class="modal fade" id="addModuleModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Create</h4>
							</div>
							<form id="addModuleForm">
								<div class="modal-body">
										<input type="hidden" id="relatedCourseID" name="relatedCourseID" value="${course.courseID}">
										
										<input type="text" id="addedModuleTitle" name="addedModuleTitle" placeholder="Title">
										
										<input type="text" id="addedModuleOverview" name="addedModuleOverview" placeholder="Overview">
										
										<input type="text" id="addedModuleContent" name="addedModuleContent" placeholder="Content">
								</div>
								<div class="modal-footer">
									<button type="button" id="addModuleButtonModal" class="addEditBtns btn btn-success">Create</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			
<!-- Add new Quiz modal -->
            <div class="modal fade" id="addQuizModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Create</h4>
							</div>
							<form id="addQuizForm">
								<div class="modal-body">
										<input type="hidden" id="addQuizCourseID" name="addQuizCourseID" value="${course.courseID}">
										
										<input type="text" id="addedQuizName" name="addedQuizName" placeholder="Name">
										
										<input type="text" id="addedQuizOverview" name="addedQuizOverview" placeholder="Overview">
								</div>
								<div class="modal-footer">
									<button type="button" id="addQuizButtonModal" class="addEditBtns btn btn-success">Create</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>		
			
<!-- Edit Module modal -->
            <div class="modal fade" id="editModuleModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Modify</h4>
							</div>
							<form id="editModuleForm">
								<div class="modal-body">
									<input type="hidden" id="moduleID-edit" name="moduleID-edit" >
									<input type="hidden" id="relatedCourseID-edit" name="relatedCourseID-edit" value="${course.courseID}" >
										
									<input type="text" id="editedModuleTitle" name="editedModuleTitle" placeholder="Title">
										
									<input type="text" id="editedModuleOverview" name="editedModuleOverview" placeholder="Overview">
										
									<input type="text" id="editedModuleContent" name="editedModuleContent" placeholder="Content">
								</div>
								<div class="modal-footer">
									<button type="button" id="editModuleButtonModal" class="addEditBtns btn btn-success">Save</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

<!-- Edit Quiz modal -->
            <div class="modal fade" id="editQuizModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Modify</h4>
							</div>
							<form id="editQuizForm">
								<div class="modal-body">
									<input type="hidden" id="quizID-edit" name="quizID-edit" >
									<input type="hidden" id="editQuizCourseID" name="editQuizCourseID" value="${course.courseID}" >
										
									<input type="text" id="editedQuizName" name="editedQuizName" placeholder="Name">
										
									<input type="text" id="editedQuizOverview" name="editedQuizOverview" placeholder="Overview">
								</div>
								<div class="modal-footer">
									<button type="button" id="editQuizButtonModal" class="addEditBtns btn btn-success">Save</button>
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