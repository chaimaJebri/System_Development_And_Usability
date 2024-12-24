<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Quiz Result</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/quizResult.css" rel="stylesheet"/> <!-- My CSS file -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/jokeScript.js" type="text/javascript"></script> <!-- My Script file -->
</head>
<body>

		<div class="navbarContainer">
			<form action="./RetrieveModulesAndQuizzes" method="get" class="navbarComponent">
				<input type="hidden" name="courseID" value="${courseID}">
				<input type="hidden" name="userID" value="${userID}">
				<button type="submit" class="btn btn-dark"><i class="bi bi-arrow-left-circle"></i> Back to Modules & Quizzes</button>
			</form>
		</div>

	<c:choose>
	
		<c:when test="${not empty pass}">
			<h1>Congratulations &#127881;</h1>
			<h2>${pass}</h2>
			<h3>As a reward, you can enjoy some coding jokes to have some fun by clicking the <strong>Laugh it Up</strong> button.</h3>
			
			<div class="btnsGroup">
				<form action="./RetrieveQuestionsAndOptions" method="get">
					<input type="hidden" name="quizID" value="${quizID}">
					<input type="hidden" name="courseID" value="${courseID}">
					<input type="hidden" name="userID" value="${userID}">
					<button type="submit" class="btn btn-secondary">Retake Quiz</button>
				</form>
				
		 		<button onclick="tellMeAJoke()" class="laughBtn btn btn-success">Laugh it Up</button>
		 		
				<form action="./RetrieveQuestionsAndOptions" method="get">
					<input type="hidden" name="quizID" value="${quizID}">
					<input type="hidden" name="courseID" value="${courseID}">
					<input type="hidden" name="userID" value="${userID}">
					<input type="hidden" name="displayAnswers" value="displayAnswers">
					<button type="submit" class="btn btn-secondary">Check Correct Answers</button>
				</form>
			</div>
			
			<div id="jokeContainer"></div>
		</c:when>
		
		<c:when test="${not empty fail}">
			<div class="failQuiz">
				<h1>Oups &#128577;</h1>
				<h2>${fail}</h2>
				
				<div class="btnsGroup">
					<form action="./RetrieveQuestionsAndOptions" method="get">
						<input type="hidden" name="quizID" value="${quizID}">
						<input type="hidden" name="courseID" value="${courseID}">
						<input type="hidden" name="userID" value="${userID}">
						<button type="submit" class="btn btn-secondary">Retake Quiz</button>
					</form>
				
					<form action="./RetrieveQuestionsAndOptions" method="get">
						<input type="hidden" name="quizID" value="${quizID}">
						<input type="hidden" name="courseID" value="${courseID}">
						<input type="hidden" name="userID" value="${userID}">
						<input type="hidden" name="displayAnswers" value="displayAnswers">
						<button type="submit" class="btn btn-secondary">Check Correct Answers</button>
					</form>
				</div>
			</div>
		</c:when>
	
	</c:choose>

	


</body>
</html>