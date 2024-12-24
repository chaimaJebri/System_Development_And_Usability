<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Module Content</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/retrieveModuleContent.css" rel="stylesheet"/> <!-- My CSS file -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	
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
	
	<c:when test="${not empty module}">
		<div class="container">
			<h1>${module.moduleTitle}</h1>
			<p>${module.moduleContent}</p>
		</div>
	</c:when>
	
	<c:otherwise>
		<h1 class="moduleNotFoundHeader">${moduleNotFound} &#128577;</h1>
	</c:otherwise>

</c:choose>



</body>
</html>
