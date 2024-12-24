$(document).ready(function()
{
	//Function responsible for handling the toast notifications
	function ToastNotification (message)
	{
		var toastNotifications = new bootstrap.Toast(document.getElementById('toastNotifications'),{ //call the toastNotifications created in the jsp file
			autohide: true, //if the user didn't close the toast, it will appear after 3s
			delay: 3000
		});
		
		$('#toastNotifications .toast-body').text(message); //put the message I want to show in the toast body then display the toast
		toastNotifications.show();
		
		toastNotifications._element.addEventListener('hidden.bs.toast',function(){ //when the toast dissapears, the page will be reloaded
			location.reload();
		});
	}
	
	//Add new course
	$('#addCourseModal').on('show.bs.modal', function () {
		$(this).find('input[type=text]').val(''); //clear the addCourseModal inputs for next use
		$('#addedCourseNameError').text('');      //clear the error containers
		$('#addedDescriptionError').text('');
	 });
	
	$('#addCourseButtonModel').on('click', function(){
		var formData = $('#addCourseForm').serialize();
		//client side validation
		var courseName=$('#addedCourseName').val().trim();
		var description=$('#addedDescription').val().trim();
		var isValid=true;
		
		$('#addedCourseNameError').text('');      //clear the error containers
		$('#addedDescriptionError').text('');
		
		if (courseName === "")
		{
            $('#addedCourseNameError').text('Please provide the name of the course');
            isValid=false;
        }
        if (description === "")
		{
            $('#addedDescriptionError').text('Please provide a description for the course');
            isValid=false;
        }
        if(!isValid)
        {
			return;
		}
		$.ajax({
			url:'./AddCourse',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#addCourseModal').modal('hide');   
                ToastNotification(response);    
            }, 
            error: function(error) 
            {
                console.log("Error occurred while adding a new course: " + error);
            }
		});
	});
	
	//Delete Course
	$(document).on('click', '.deleteCourseButton', function(){
		var courseID=$(this).val();
		$('#deleteCourseModal').data('courseID',courseID);
		$('#deleteCourseModal').modal('show');
	});
	
	$('#deleteCourseButtonModal').on('click',function(){ 
		var courseID=$('#deleteCourseModal').data('courseID');
		
		$.ajax({
			url: './DeleteCourse',
			type:'POST',
			cache:false,
			data: {courseID:courseID},
			success: function(response)
			{
				$('#deleteCourseModal').modal('hide');
				ToastNotification(response);
			},
			error: function (error)
			{
				console.log("Error occurred while deleting a course: " + error);
			}
		});
	});
	
	//Edit Course
	$('.editCourseButton').click(function(){
		var courseID = $(this).val();                                                //Course ID from the button value
		var courseName =$(this).closest('.card-body').find('.card-title a').text(); // Course Name from the card title
		var description = $(this).closest('.card-body').find('.card-text').text(); // Description from the card text
		
		$('#editedCourseNameError').text('');      //clear the error containers
		$('#editedDescriptionError').text('');
		
		//Fill the modal before opening it
		$('#courseID-edit').val(courseID);
		$('#editedCourseName').val(courseName);
		$('#editedDescription').val(description);
		 
		//Now show the modal
		$('#editCourseModal').modal('show');
	});
	
	$('#editCourseButtonModal').on('click',function(){
		var formData = $('#editCourseForm').serialize();
		//client side validation
		var courseName=$('#editedCourseName').val().trim();
		var description=$('#editedDescription').val().trim();
		var isValid=true;
		
		$('#editedCourseNameError').text('');      //clear the error containers
		$('#editedDescriptionError').text('');
		
		if (courseName === "")
		{
            $('#editedCourseNameError').text('Please provide the name of the course');
            isValid=false;
        }
        if (description === "")
		{
            $('#editedDescriptionError').text('Please provide a description for the course');
            isValid=false;
        }
        if(!isValid)
        {
			return;
		}
		
		$.ajax({
			url:'./EditCourse',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editCourseModal').modal('hide');
				ToastNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while editing course details: " + error);
            }
		});
	});
	
	
	//Search for Courses by Name
	$('#searchCourse').on('input', function(){
		var searchCourse = $(this).val();
		var userID =$('#userID').val();
		
		$.ajax ({
			url: './RetrieveCourses',
			type: 'GET',
			cache: false,
			data: {
				searchCourse : searchCourse,
				userID: userID
				},
			success: function(response)
			{
                $('#courseCards').html($(response).find('#courseCards').html());     //get the cards of the searched courses (search result) and put them in the jsp page 
			},
			error: function(error)
			{
				console.log("Error occured while searching courses: "+error);
			}
		});
	});
	
	
});