$(document).ready(function()
{
	//Edit Question
	$('.editQuestionButton').click(function(){
		var questionID = $(this).val();                                                //questionID from the button value
		var questionText =$(this).closest('.list-group-item').find('.questionTextClass').text(); // quizName from the card title
		
		
		$('#editedQuestionTextError').text('');
		
		//Fill the modal before opening it
		$('#questionID-edit').val(questionID);
		$('#editedQuestionText').val(questionText);
		 
		//Now show the modal
		$('#editQuestionModal').modal('show');
	});
	
	$('#editQuestionButtonModal').on('click', function(){
		var formData = $('#editQuestionForm').serialize();
		
		//client side validation
		var questionText=$('#editedQuestionText').val().trim();
		var isValid=true;
		
		$('#editedQuestionTextError').text('');
		
		if (questionText === "")
		{
            $('#editedQuestionTextError').text('Please provide the question text');
            isValid=false;
        }
        
        if(!isValid)
        {
			return;
		}
		
		$.ajax({
			url:'./EditQuestion',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editQuestionModal').modal('hide');
				ToastNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while editing question details: " + error);
            }
		});
		
	})
	
	//--------------------------------------------------------------------------------------------------------
	
	//Delete Question
	$(document).on('click', '.deleteQuestionButton', function(){
		var questionID=$(this).val();
		$('#deleteQuestionModal').data('questionID',questionID);
		$('#deleteQuestionModal').modal('show');
	});
	$('#deleteQuestionButtonModal').on('click',function(){ 
		var questionID=$('#deleteQuestionModal').data('questionID');
		
		$.ajax({
			url: './DeleteQuestion',
			type:'POST',
			cache:false,
			data: {questionID: questionID},
			success: function(response)
			{
				$('#deleteQuestionModal').modal('hide');
				ToastNotification(response);
			},
			error: function (error)
			{
				console.log("Error occurred while deleting a question: " + error);
			}
		});
	}); 
	
	//--------------------------------------------------------------------------------------------------------
	
	//Add New Question and its associated options
	$('#addQuestionOptionsModal').on('show.bs.modal', function () {
		$(this).find('input[type=text]').val('');   //clear the Modal inputs for next use
		
		$(this).find('input[type=checkbox]').prop('checked', false); //clear all checkboxes
		
		$('#addedQuestionTextError').text('');      //clear the error containers
		$('#validateProvidedOptions-add').text('');
		$('#validateCorrectOption-add').text('');
		
	 });
	
	 $('#addButtonModal').on('click', function (){
		 var quizID= $("#quizID-add").val();
		 var questionText = $("#addedQuestionText").val().trim();
		 
		 //the data to send to the server
		 var formData = {
			 quizID: quizID,
			 questionText: questionText
		 }
		 
		 var providedOptionText = [];
         for (var i = 1; i <= 4; i++) {
            var optionText = $("#addedOptionText" + i).val().trim();
            var isCorrect = $("#addedIsCorrect" + i).is(":checked");
            if (optionText !== "") {
                formData['optionText' + i] = optionText;
                formData['isCorrect' + i] = isCorrect;
                providedOptionText.push({ optionText: optionText, isCorrect: isCorrect });
            }
         }
		 
		 var isValid=true;
		 
		 $('#addedQuestionTextError').text('');      //clear the error containers
		 $('#validateProvidedOptions-add').text('');
		 $('#validateCorrectOption-add').text('');
		 
		 if (questionText=== "")
		 {
			 $("#addedQuestionTextError").text("Please provide the question text");
			 isValid=false;
		 }
		 
		 // Check if at least two options are provided
         if (providedOptionText.length < 2) {
	        $("#validateProvidedOptions-add").text("The question needs to have at least two options.");
	        isValid = false;
  		 }
		 
		 //between the provided options, only one should be marked as correct
		 var correctOptionsNumber = providedOptionText.filter(function(option){
			 return option.isCorrect;
		 }).length;
		 
		 if(correctOptionsNumber !== 1)
		 {
			 $("#validateCorrectOption-add").text("From the options, Only one option should be correct");
			 isValid=false;
		 }
		 
		 
		 if(!isValid)
		 {
			 return;
		 }
		 
		 console.log(formData)
		 
		 $.ajax({
			url:'./AddQuestionOptions',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#addQuestionOptionsModal').modal('hide');
				ToastNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while adding question and options: " + error);
            }
		});
		 
	 });
	 
	 //-------------------------------------------------------------------------------------------------------

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
	
	
});