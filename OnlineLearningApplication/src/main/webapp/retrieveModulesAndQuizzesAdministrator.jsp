<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Modules & Quizzes Directory</title>
	
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
				<button type="submit" class="btn btn-dark"><i class="bi bi-arrow-left-circle"></i> Back to all courses</button>
			</form>
		</div>
		
		<h1 class="courseNotFoundHeader">${courseNotFound} &#128577;</h1>
		
	</c:when>
	
	
	<c:otherwise>
	
		<div class="navbarContainer">
			<form action="./RetrieveCourses" method="get" class="navbarComponent">
				<input type="hidden" name="userID" value="${userID}">
				<button type="submit" class="btn btn-dark"><i class="bi bi-arrow-left-circle"></i> Back to all courses</button>
			</form>
		
			<form class="navbarComponent">
				<input type="text" id="searchModulesAndQuizzes" placeholder="Search modules & quizzes &#128269; ...">
			</form>
			
			<div class="addMQBtn navbarComponent">
				<button data-bs-toggle="modal" data-bs-target="#addModuleModal" class="btn btn-dark"><i class="bi bi-plus-circle"></i> Add New Module</button>
			
				<button data-bs-toggle="modal" data-bs-target="#addQuizModal" class="btn btn-dark"><i class="bi bi-plus-circle"></i> Add New Quiz</button>
			</div>
		</div>	
	
		
			
		<h1>Modules & Quizzes For The ${course.courseName} Course (ID: ${course.courseID})</h1>	
		
		<!-- Modules Section -->
		
		<h2>Modules</h2>
		
		<c:choose>
			<c:when test="${not empty noModules}">
				<h3>${noModules} &#128577;</h3>
			</c:when>
			
			<c:otherwise>
				<div class="container">
					<div id= "moduleCards" class="row row-cols-1 row-cols-md-3 g-4">
						<c:forEach var="module" items="${courseModules}">
							<div class="col">
								<div class="card module-quiz-card">
						            <div class="card-body">
						            	<h6>Module ID: ${module.moduleID}</h6>
							            <h5 class="card-title"><a href="./RetrieveModuleContent?moduleID=${module.moduleID}&courseID=${module.courseID}&userID=${userID}">${module.moduleTitle}</a></h5>
							            <p class="card-text">${module.moduleOverview}</p>
							            <div class="deleteEditButtons">
								            <button class="editModuleButton btn btn-outline-primary" value="${module.moduleID}" data-moduleContent="${module.moduleContent}" title="Edit"><i class="bi bi-pencil-square"></i></button>
								            <button class="deleteModuleButton btn btn-outline-danger" value="${module.moduleID}" title="Delete"><i class="bi bi-trash3-fill"></i></button>
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
		
		<h2>Quizzes</h2>
		
		<c:choose>
			<c:when test="${not empty noQuizzes}">
				<h3>${noQuizzes} &#128577;</h3>
			</c:when>
			
			<c:otherwise>
				<div class="container">
					<div id= "quizCards" class="row row-cols-1 row-cols-md-3 g-4">
						<c:forEach var="quiz" items="${courseQuizzes}">
							<div class="col">
								<div class="card module-quiz-card">
						            <div class="card-body">
						            	<h6>Quiz ID: ${quiz.quizID}</h6>
							            <h5 class="card-title"><a href="./RetrieveQuestionsAndOptions?quizID=${quiz.quizID}&courseID=${course.courseID}&userID=${userID}">${quiz.quizName}</a></h5>
							            <p class="card-text">${quiz.quizOverview}</p>
							            <div class="deleteEditButtons">
								            <button class="editQuizButton btn btn-outline-primary" value="${quiz.quizID}" title="Edit"><i class="bi bi-pencil-square"></i></button>
								            <button class="deleteQuizButton btn btn-outline-danger" value="${quiz.quizID}" title="Delete"><i class="bi bi-trash3-fill"></i></button>
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
								<h4>Add New Module for Course with ID:'${course.courseID}'</h4>
							</div>
							<form id="addModuleForm">
								<div class="modal-body">
										<input type="hidden" id="relatedCourseID" name="relatedCourseID" value="${course.courseID}">
										
										<input type="text" id="addedModuleTitle" name="addedModuleTitle" placeholder="Enter Module Title">
										<span id="addedModuleTitleError" class="text-danger"></span>
										
										<input type="text" id="addedModuleOverview" name="addedModuleOverview" placeholder="Enter Module Overview">
										<span id="addedModuleOverviewError" class="text-danger"></span>
										
										<input type="text" id="addedModuleContent" name="addedModuleContent" placeholder="Enter Module Content">
										<span id="addedModuleContentError" class="text-danger"></span>	
								</div>
								<div class="modal-footer">
									<button type="button" id="addModuleButtonModal" class="addEditBtns btn btn-success">Add Module</button>
									<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
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
								<h4>Add New Quiz for Course with ID:'${course.courseID}'</h4>
							</div>
							<form id="addQuizForm">
								<div class="modal-body">
										<input type="hidden" id="addQuizCourseID" name="addQuizCourseID" value="${course.courseID}">
										
										<input type="text" id="addedQuizName" name="addedQuizName" placeholder="Enter Quiz Name">
										<span id="addedQuizNameError" class="text-danger"></span>
										
										<input type="text" id="addedQuizOverview" name="addedQuizOverview" placeholder="Enter Quiz Overview">
										<span id="addedQuizOverviewError" class="text-danger"></span>
								</div>
								<div class="modal-footer">
									<button type="button" id="addQuizButtonModal" class="addEditBtns btn btn-success">Add Quiz</button>
									<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			
			
<!-- Delete Module modal -->	
			 <div class="modal fade" id="deleteModuleModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Delete Module</h4>
							</div>
							<div class="modal-body">
								<p>Are you sure you want to delete this Module permanently?</p>
							</div>
							<div class="modal-footer">
								<button type="button" id="deleteModuleButtonModal" class="btn btn-danger">Delete Module</button>
								<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
<!-- Delete Quiz modal -->	
			 <div class="modal fade" id="deleteQuizModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Delete Quiz</h4>
							</div>
							<div class="modal-body">
								<p>Are you sure you want to delete this Quiz permanently?</p>
							</div>
							<div class="modal-footer">
								<button type="button" id="deleteQuizButtonModal" class="btn btn-danger">Delete Quiz</button>
								<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
							</div>
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
								<h4>Edit Module Details</h4>
							</div>
							<form id="editModuleForm">
								<div class="modal-body">
									<input type="hidden" id="moduleID-edit" name="moduleID-edit" >
									<input type="hidden" id="relatedCourseID-edit" name="relatedCourseID-edit" value="${course.courseID}" >
										
									<input type="text" id="editedModuleTitle" name="editedModuleTitle" placeholder="Enter Module Title">
									<span id="editedModuleTitleError" class="text-danger"></span>
										
									<input type="text" id="editedModuleOverview" name="editedModuleOverview" placeholder="Enter Module Overview">
									<span id="editedModuleOverviewError" class="text-danger"></span>
										
									<input type="text" id="editedModuleContent" name="editedModuleContent" placeholder="Enter Module Content">
									<span id="editedModuleContentError" class="text-danger"></span>
								</div>
								<div class="modal-footer">
									<button type="button" id="editModuleButtonModal" class="addEditBtns btn btn-success">Save Changes</button>
									<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
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
								<h4>Edit Quiz Details</h4>
							</div>
							<form id="editQuizForm">
								<div class="modal-body">
									<input type="hidden" id="quizID-edit" name="quizID-edit" >
									<input type="hidden" id="editQuizCourseID" name="editQuizCourseID" value="${course.courseID}" >
										
									<input type="text" id="editedQuizName" name="editedQuizName" placeholder="Enter Quiz Name">
									<span id="editedQuizNameError" class="text-danger"></span>
										
									<input type="text" id="editedQuizOverview" name="editedQuizOverview" placeholder="Enter Quiz Overview">
									<span id="editedQuizOverviewError" class="text-danger"></span>			
								</div>
								<div class="modal-footer">
									<button type="button" id="editQuizButtonModal" class="addEditBtns btn btn-success">Save Changes</button>
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
		
	</c:otherwise>
	
</c:choose>


</body>
</html>