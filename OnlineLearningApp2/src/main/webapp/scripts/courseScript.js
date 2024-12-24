$(document).ready(function()
{
	//-------------------------------------------------------------------
	
	//Add new course
	$('#addCourseButtonModel').on('click', function(){
		var formData = $('#addCourseForm').serialize();
		
		$.ajax({
			url:'./AddCourse',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#addCourseModal').modal('hide'); 
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Something went wrong" + error);
            }
		});
	});
	
	//Delete Course
	$(document).on('click', '.deleteCourseButton', function(){
		var courseID=$(this).val();
		
		$.ajax({
			url: './DeleteCourse',
			type:'POST',
			cache:false,
			data: {courseID:courseID},
			success: function(response)
			{
				console.log(response);
			},
			error: function (error)
			{
				console.log("Something went wrong " + error);
			}
		});
	});
	
	
	//Edit Course
	$('.editCourseButton').click(function(){
		var courseID = $(this).val();      
		
		//Fill the modal before opening it
		$('#courseID-edit').val(courseID);
		 
		//Now show the modal
		$('#editCourseModal').modal('show');
	});
	
	$('#editCourseButtonModal').on('click',function(){
		var formData = $('#editCourseForm').serialize();
		
		$.ajax({
			url:'./EditCourse',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editCourseModal').modal('hide');
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Something went wrong " + error);
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
				console.log("Something went wrong: "+error);
			}
		});
	});
	
	
});