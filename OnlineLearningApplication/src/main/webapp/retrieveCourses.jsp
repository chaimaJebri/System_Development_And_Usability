<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Courses Directory</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> <!-- Bootstrap Icons Library CSS -->
	<link href="./css/retrieveCourses.css" rel="stylesheet"/> <!-- My CSS file -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery Library -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script> <!-- Bootstrap Script -->
	<script src="./scripts/courseScript.js" type="text/javascript"></script> <!-- My Script file -->
</head>
<body>

<div class="navbarContainer">
	<form method="post" action="./AdministratorPortal" class="navbarComponent">
		<input type="hidden" name="userID" value="${userID}">
		<button type="submit" title="Back to administrator portal" class="btn btn-dark"><i class="bi bi-house"></i> Home</button>
	</form>
	
	<form class="navbarComponent">
		<input type="text" id="searchCourse" placeholder="Search courses by name &#128269; ...">
	</form> 
	
	<button class="navbarComponent btn btn-dark" data-bs-toggle="modal" data-bs-target="#addCourseModal"><i class="bi bi-plus-circle"></i> Add New Course</button>
</div>

<h1>Available Courses</h1>

<div class="container">
	<div id= "courseCards" class="row row-cols-1 row-cols-md-3 g-4">
		<c:forEach var="course" items="${courses}">
			<div class="col">
				<div class="card course-card">
					<img src="images/courseImg.jpg" class="card-img-top" alt="Course Image">
		            <div class="card-body">
		            	<h6>Course ID: ${course.courseID}</h6>
			            <h5 class="card-title"><a href="./RetrieveModulesAndQuizzes?courseID=${course.courseID}&userID=${userID}">${course.courseName}</a></h5>
			            <p class="card-text">${course.description}</p>
			            <div class="deleteEditButtons">
				            <button class="editCourseButton btn btn-outline-primary" value="${course.courseID}" title="Edit"><i class="bi bi-pencil-square"></i></button>
				            <button class="deleteCourseButton btn btn-outline-danger" value="${course.courseID}" title="Delete"><i class="bi bi-trash3-fill"></i></button>
			            </div>
		            </div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<input type="hidden" id="userID" value="${userID}">

<!-- Add new course modal -->
            <div class="modal fade" id="addCourseModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class=form-container>
							<div class="modal-header">
								<h4>Add New Course</h4>
							</div>
							<form id="addCourseForm">
								<div class="modal-body">			
										<input type="text" id="addedCourseName" name="addedCourseName" placeholder="Enter Course Name">
										<span id="addedCourseNameError" class="text-danger"></span>
										
										<input type="text" id="addedDescription" name="addedDescription" placeholder="Enter Course Description ">
										<span id="addedDescriptionError" class="text-danger"></span>
								</div>
								<div class="modal-footer">
									<button type="button" id="addCourseButtonModel" class="addEditBtns btn btn-success">Add Course</button>
									<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

<!-- Delete course modal -->	
			 <div class="modal fade" id="deleteCourseModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Delete Course</h4>
							</div>
							<div class="modal-body">
								<p>Are you sure you want to delete this Course permanently?</p>
							</div>
							<div class="modal-footer">
								<button type="button" id="deleteCourseButtonModal" class="btn btn-danger">Delete Course</button>
								<button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Cancel</button>
							</div>
						</div>
					</div>
				</div>
			</div>


<!-- Edit course modal -->
            <div class="modal fade" id="editCourseModal" data-bs-backdrop="static">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="form-container">
							<div class="modal-header">
								<h4>Edit Course Details</h4>
							</div>
							<form id="editCourseForm">
								<div class="modal-body">	
										<input type="hidden" id="courseID-edit" name="courseID-edit" >
										
										<input type="text" id="editedCourseName" name="editedCourseName" placeholder="Enter Course Name">
										<span id="editedCourseNameError" class="text-danger"></span>
										
										<input type="text" id="editedDescription" name="editedDescription" placeholder="Enter Course Description ">
										<span id="editedDescriptionError" class="text-danger"></span>
								</div>
								<div class="modal-footer">
									<button type="button" id="editCourseButtonModal" class="addEditBtns btn btn-success">Save Changes</button>
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