$(document).ready(function()
{
	//Edit Question
	$('.editQuestionButton').click(function(){
		var questionID = $(this).val();                                                //questionID from the button value
		
		//Fill the modal before opening it
		$('#questionID-edit').val(questionID);
		 
		//Now show the modal
		$('#editQuestionModal').modal('show');
	});
	
	$('#editQuestionButtonModal').on('click', function(){
		var formData = $('#editQuestionForm').serialize();
		
		$.ajax({
			url:'./EditQuestion',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#editQuestionModal').modal('hide');
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Something went wrong: " + error);
            }
		});
		
	})
	
	//--------------------------------------------------------------------------------------------------------
	
	//Delete Question
	$(document).on('click', '.deleteQuestionButton', function(){
		var questionID=$(this).val();
		
		$.ajax({
			url: './DeleteQuestion',
			type:'POST',
			cache:false,
			data: {questionID: questionID},
			success: function(response)
			{
				console.log(response);
			},
			error: function (error)
			{
				console.log("Something went wrong: " + error);
			}
		});
	});
	
	//--------------------------------------------------------------------------------------------------------
	
	//Add New Question and its associated options
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
		 
		 $('#validateProvidedOptions-add').text('');
		 $('#validateCorrectOption-add').text('');
		 
		 // Check if at least two options are provided
         if (providedOptionText.length < 2) {
	        $("#validateProvidedOptions-add").text("Error");
	        isValid = false;
  		 }
		 
		 //between the provided options, only one should be marked as correct
		 var correctOptionsNumber = providedOptionText.filter(function(option){
			 return option.isCorrect;
		 }).length;
		 
		 if(correctOptionsNumber !== 1)
		 {
			 $("#validateCorrectOption-add").text("Error");
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
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Something went wrong" + error);
            }
		});
		 
	 });
	 
	
});