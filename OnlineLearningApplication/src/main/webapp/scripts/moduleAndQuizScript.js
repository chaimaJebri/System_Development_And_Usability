$(document).ready(function()
{
	//Edit Quiz
	$('.editQuizButton').click(function(){
		var quizID = $(this).val();                                                //quizID from the button value
		var quizName =$(this).closest('.card-body').find('.card-title a').text(); // quizName from the card title
		var quizOverview = $(this).closest('.card-body').find('.card-text').text(); // quizOverview from the card text
		
		$('#editedQuizNameError').text('');
		$('#editedQuizOverviewError').text('');
		
		//Fill the modal before opening it
		$('#quizID-edit').val(quizID);
		$('#editedQuizName').val(quizName);
		$('#editedQuizOverview').val(quizOverview);
		 
		//Now show the modal
		$('#editQuizModal').modal('show');
	});
	
	$('#editQuizButtonModal').on('click', function(){
		var formData = $('#editQuizForm').serialize();
		
		//client side validation
		var quizName=$('#editedQuizName').val().trim();
		var quizOverview=$('#editedQuizOverview').val().trim();
		var isValid=true;
		
		$('#editedQuizNameError').text('');
		$('#editedQuizOverviewError').text('');
		
		if (quizName === "")
		{
            $('#editedQuizNameError').text('Please provide the name of the quiz');
            isValid=false;
        }
        if (quizOverview === "")
		{
            $('#editedQuizOverviewError').text('Please provide the overview of the quiz');
            isValid=false;
        }
        
        if(!isValid)
        {
			return;
		}
		
		$.ajax({
			url:'./EditQuiz',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editQuizModal').modal('hide');
				ToastNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while editing quiz details: " + error);
            }
		});
		
	})
	
	
	
	//Delete Quiz
	$(document).on('click', '.deleteQuizButton', function(){
		var quizID=$(this).val();
		$('#deleteQuizModal').data('quizID',quizID);
		$('#deleteQuizModal').modal('show');
	});
	
	$('#deleteQuizButtonModal').on('click',function(){ 
		var quizID=$('#deleteQuizModal').data('quizID');
		
		$.ajax({
			url: './DeleteQuiz',
			type:'POST',
			cache:false,
			data: {quizID: quizID},
			success: function(response)
			{
				$('#deleteQuizModal').modal('hide');
				ToastNotification(response);
			},
			error: function (error)
			{
				console.log("Error occurred while deleting a quiz: " + error);
			}
		});
	}); 
	
	
	//Add New Quiz
	$('#addQuizModal').on('show.bs.modal', function () {
		$(this).find('input[type=text]').val(''); //clear the addQuizModal inputs for next use
		$('#addedQuizNameError').text('');      //clear the error containers
		$('#addedQuizOverviewError').text('');
	 });
	 
	 $('#addQuizButtonModal').on('click', function (){
		var formData = $('#addQuizForm').serialize();
		//client side validation
		var quizName =$('#addedQuizName').val().trim();
		var quizOverview = $('#addedQuizOverview').val().trim();
		var isValid=true;
		 
		$('#addedQuizNameError').text('');
		$('#addedQuizOverviewError').text('');
		 
		if (quizName === "")
		{
			$('#addedQuizNameError').text('Please provide the name of the quiz');
			isValid=false;
		}
		if(quizOverview === "")
		{
			$('#addedQuizOverviewError').text('Please provide the overview of the quiz');
			isValid=false;
		}
		
		if(!isValid)
		{
			return;
		}
		 
		$.ajax({
			url:'./AddQuiz',
			type:'Post',
			cache:false,
			data:formData,
			success: function(response)
			{
				$('#addQuizModal').modal('hide');
				ToastNotification(response);   
			},
			error:function(error)
			{
				console.log("Error occurred while adding a new quiz: " + error);
			}
		});
		 
	 });
	
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
	
	
	//Add New Module
	$('#addModuleModal').on('show.bs.modal', function () {
		$(this).find('input[type=text]').val(''); //clear the addModuleModal inputs for next use
		$('#addedModuleTitleError').text('');      //clear the error containers
		$('#addedModuleOverviewError').text('');
		$('#addedModuleContentError').text('');
	 });
	 
	$('#addModuleButtonModal').on('click', function (){
		var formData = $('#addModuleForm').serialize();
		//client side validation
		var moduleTitle =$('#addedModuleTitle').val().trim();
		var moduleOverview = $('#addedModuleOverview').val().trim();
		var moduleContent= $('#addedModuleContent').val().trim();
		var isValid=true;
		 
		$('#addedModuleTitleError').text('');
		$('#addedModuleOverviewError').text('');
		$('#addedModuleContentError').text('');
		 
		if (moduleTitle === "")
		{
			$('#addedModuleTitleError').text('Please provide the title of the module');
			isValid=false;
		}
		if(moduleOverview === "")
		{
			$('#addedModuleOverviewError').text('Please provide the overview of the module');
			isValid=false;
		}
		if(moduleContent === "")
		{
			$('#addedModuleContentError').text('Please provide the content of the module');
			isValid=false;
		}
		if(!isValid)
		{
			return;
		}
		 
		$.ajax({
			url:'./AddModule',
			type:'Post',
			cache:false,
			data:formData,
			success: function(response)
			{
				$('#addModuleModal').modal('hide');
				ToastNotification(response);   
			},
			error:function(error)
			{
				console.log("Error occurred while adding a new module: " + error);
			}
		});
		 
	 });
	 
	//Delete Module
	$(document).on('click', '.deleteModuleButton', function(){
		var moduleID=$(this).val();
		$('#deleteModuleModal').data('moduleID',moduleID);
		$('#deleteModuleModal').modal('show');
	});
	 
	$('#deleteModuleButtonModal').on('click',function(){ 
		var moduleID=$('#deleteModuleModal').data('moduleID');
		
		$.ajax({
			url: './DeleteModule',
			type:'POST',
			cache:false,
			data: {moduleID:moduleID},
			success: function(response)
			{
				$('#deleteModuleModal').modal('hide');
				ToastNotification(response);
			},
			error: function (error)
			{
				console.log("Error occurred while deleting a module: " + error);
			}
		});
	}); 
	
	

	//Edit Module
	$('.editModuleButton').click(function(){
		var moduleID = $(this).val();                                                //module ID from the button value
		var moduleTitle =$(this).closest('.card-body').find('.card-title a').text(); // moduleTitle from the card title
		var moduleOverview = $(this).closest('.card-body').find('.card-text').text(); // module overview from the card text
		var moduleContent =$(this).attr('data-moduleContent');
		
		$('#editedModuleTitleError').text('');
		$('#editedModuleOverviewError').text('');
		$('#editedModuleContentError').text('');
		
		//Fill the modal before opening it
		$('#moduleID-edit').val(moduleID);
		$('#editedModuleTitle').val(moduleTitle);
		$('#editedModuleOverview').val(moduleOverview);
		$('#editedModuleContent').val(moduleContent);
		 
		//Now show the modal
		$('#editModuleModal').modal('show');
	});
	
	$('#editModuleButtonModal').on('click', function(){
		var formData = $('#editModuleForm').serialize();
		
		//client side validation
		var moduleTitle=$('#editedModuleTitle').val().trim();
		var moduleOverview=$('#editedModuleOverview').val().trim();
		var moduleContent= $('#editedModuleContent').val().trim();
		var isValid=true;
		
		$('#editedModuleTitleError').text('');
		$('#editedModuleOverviewError').text('');
		$('#editedModuleContentError').text('');
		
		if (moduleTitle === "")
		{
            $('#editedModuleTitleError').text('Please provide the title of the module');
            isValid=false;
        }
        if (moduleOverview === "")
		{
            $('#editedModuleOverviewError').text('Please provide the overview of the module');
            isValid=false;
        }
        if (moduleContent === "")
		{
            $('#editedModuleContentError').text('Please provide the content of the module');
            isValid=false;
        }
        
        if(!isValid)
        {
			return;
		}
		
		$.ajax({
			url:'./EditModule',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editModuleModal').modal('hide');
				ToastNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while editing module details: " + error);
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
				console.log("Error occured while searching modules and quizzes: "+error);
			}
		});
	});
	
	
});