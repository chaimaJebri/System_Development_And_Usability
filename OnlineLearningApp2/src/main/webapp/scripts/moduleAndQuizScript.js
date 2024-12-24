$(document).ready(function()
{
	//--------------------------------------------------------------------------------------
	
	//Edit Quiz
	$('.editQuizButton').click(function(){
		var quizID = $(this).val(); // quizOverview from the card text
		
		//Fill the modal before opening it
		$('#quizID-edit').val(quizID);
		 
		//Now show the modal
		$('#editQuizModal').modal('show');
	});
	
	$('#editQuizButtonModal').on('click', function(){
		var formData = $('#editQuizForm').serialize();
		
		$.ajax({
			url:'./EditQuiz',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editQuizModal').modal('hide');
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred: " + error);
            }
		});
		
	})
	
	
	
	//Delete Quiz
	$(document).on('click', '.deleteQuizButton', function(){
		var quizID=$(this).val();
		
		$.ajax({
			url: './DeleteQuiz',
			type:'POST',
			cache:false,
			data: {quizID: quizID},
			success: function(response)
			{
				console.log(response);
			},
			error: function (error)
			{
				console.log("Error occurred: " + error);
			}
		});
	});
	
	//Add New Quiz 
	 $('#addQuizButtonModal').on('click', function (){
		var formData = $('#addQuizForm').serialize();
		 
		$.ajax({
			url:'./AddQuiz',
			type:'Post',
			cache:false,
			data:formData,
			success: function(response)
			{
				$('#addQuizModal').modal('hide');
				console.log(response);
			},
			error:function(error)
			{
				console.log("Something went wrong: " + error);
			}
		});
		 
	 });
	
	//Add New Module 
	$('#addModuleButtonModal').on('click', function (){
		var formData = $('#addModuleForm').serialize();
		 
		$.ajax({
			url:'./AddModule',
			type:'Post',
			cache:false,
			data:formData,
			success: function(response)
			{
				$('#addModuleModal').modal('hide');
				console.log(response);
			},
			error:function(error)
			{
				console.log("Error occurred: " + error);
			}
		});
		 
	 });
	 
	 
	//Delete Module
	$(document).on('click', '.deleteModuleButton', function(){
		var moduleID=$(this).val();
		
		$.ajax({
			url: './DeleteModule',
			type:'POST',
			cache:false,
			data: {moduleID:moduleID},
			success: function(response)
			{
				console.log(response);
			},
			error: function (error)
			{
				console.log("Error occurred: " + error);
			}
		});
	});
	 

	
	

	//Edit Module
	$('.editModuleButton').click(function(){
		var moduleID = $(this).val();
		
		//Fill the modal before opening it
		$('#moduleID-edit').val(moduleID);
		 
		//Now show the modal
		$('#editModuleModal').modal('show');
	});
	
	$('#editModuleButtonModal').on('click', function(){
		var formData = $('#editModuleForm').serialize();
		
		$.ajax({
			url:'./EditModule',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editModuleModal').modal('hide');
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred: " + error);
            }
		});
		
	})
	
	//Search for Module by Title and for Quiz by Name
	$('#searchModulesAndQuizzes').on('input', function(){
		var searchModuleQuiz = $(this).val();
		var courseID = $('#relatedCourseID').val();
		var userID = $('#userID').val();
		
		$.ajax ({
			url: './RetrieveModulesAndQuizzes',
			type: 'GET',
			cache: false,
			data: {
				searchModuleQuiz : searchModuleQuiz,
				courseID : courseID,
				userID: userID
			},
			success: function(response)
			{
                $('#moduleCards').html($(response).find('#moduleCards').html());     //get the cards of the searched modules (search result) and put them in the jsp page 
				$('#quizCards').html($(response).find('#quizCards').html()); 
			},
			error: function(error)
			{
				console.log("Something went wrong: "+error);
			}
		});
	});
	
	
});