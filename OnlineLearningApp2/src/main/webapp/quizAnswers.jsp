<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Answers</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/quizAnswers.css" rel="stylesheet"/> <!-- My CSS file -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/learnerScript.js" type="text/javascript"></script> <!-- My Script file -->
</head>
<body>

	<div class="navbarContainer">
		<button class="navbarComponent btn btn-dark" id="confirmLogout">Leave</button>
	</div>	
	
	<c:choose>
	
		<c:when test="${not empty quizNotFound}">
			<h1 class="quizNotFoundHeader">${quizNotFound}</h1>
		</c:when>
		
		<c:otherwise>
		
			<h1>Answers</h1>
			
			<c:choose>
			
				<c:when test="${not empty noQuestions}">
					<h3>${noQuestions}</h3>
				</c:when>
				
				<c:otherwise>
				
					<div class="questionsOptsContainer">
						<ul class="list-group">
							<c:forEach var="question" items="${questions}" varStatus="i">
								<li class="list-group-item eachQuestion">
									<h6>Q:</h6>
									<p class="questionTextClass">${question.questionText}</p>
									
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
		</c:otherwise>
	
	</c:choose>
	
	







</body>
</html>