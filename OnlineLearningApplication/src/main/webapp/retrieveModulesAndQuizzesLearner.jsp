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
	<link href="./css/retrieveModulesAndQuizzesLearner.css" rel="stylesheet"/> <!-- My CSS file -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/learnerScript.js" type="text/javascript"></script> <!-- My Script file -->

</head>
<body>
	
	<c:choose>
	
		<c:when test="${not empty courseNotFound}">
			<div class="navbarContainer">
				<form action="./LearnerPortal" method="post" class="navbarComponent">
					<input type="hidden" name="userID" value="${userID}">
					<button type="submit" class="btn btn-dark"><i class="bi bi-arrow-left-circle"></i> Back to all courses</button>
				</form>
			</div>
	
			<h1 class="courseNotFoundHeader">${courseNotFound} &#128577;</h1>
		</c:when>
		
		
		<c:otherwise>
			<div class="navbarContainer">
				<form action="./LearnerPortal" method="post" class="navbarComponent">
					<input type="hidden" name="userID" value="${userID}">
					<button type="submit" class="btn btn-dark"><i class="bi bi-arrow-left-circle"></i> Back to all courses</button>
				</form>
				
				<form class="navbarComponent">
					<input type="text" id="searchForModulesAndQuizzes" placeholder="Search modules & quizzes &#128269; ...">
				</form>
				
				<form class="navbarComponent">
					<input type="hidden">
				</form>	
			</div>
		
			<h1>Modules & Quizzes For The ${course.courseName} Course</h1>
		
			
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
								            <h5 class="card-title"><a href="./RetrieveModuleContent?moduleID=${module.moduleID}&courseID=${module.courseID}&userID=${userID}">${module.moduleTitle}</a></h5>
								            <p class="card-text">${module.moduleOverview}</p>	            
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
								            <h5 class="card-title"><a href="./RetrieveQuestionsAndOptions?quizID=${quiz.quizID}&courseID=${course.courseID}&userID=${userID}">${quiz.quizName}</a></h5>
								            <p class="card-text">${quiz.quizOverview}</p>
							            </div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:otherwise>	
			</c:choose>
			
			<input type="hidden" id="course-ID" value="${course.courseID}">
			<input type="hidden" id="user-ID" value="${userID}">
			
		</c:otherwise>
		
	</c:choose>
	


</body>
</html>