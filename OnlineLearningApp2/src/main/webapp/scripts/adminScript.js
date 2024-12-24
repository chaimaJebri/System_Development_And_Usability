$(document).ready(function()
{
	//-------------------------------------------------------------------------------------------------
	
	//Edit the system's terms and policies
	$('#editTermsPoliciesBtn').on('click', function(event){
		event.preventDefault();
		
		var editedTermsPolicies=$('#editedTermsPolicies').val().trim();
		
        $.ajax({
			url:'./TermsAndPoliciesHandler',
			type:'POST',
			cache:false,
			data: {
				editedTermsPolicies: editedTermsPolicies
			},
			success: function(response) 
            { 
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Something went wrong: " + error);
            }
		});
		
	});
	
	
	//-----------------------------------------------------------------------------------------------
	
	$('#sendEmailBtn').on('click', function(event){
		
		event.preventDefault(); //to let the form button works only with Ajax
		$('#notifyLearnersModal').modal('show');
	});
	
	$('#sendEmailBtnModal').on('click', function(){
		
		var formData= $('#learnersTableForm').serializeArray();
		var emailSubject= $('#emailSubject').val().trim();
		var emailText= $('#emailText').val().trim();
		
		formData.push({ name: "emailSubject", value: emailSubject });
		formData.push({ name: "emailText", value: emailText });
		
		$.ajax({
			url:'./EmailNotificationsAPI',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#notifyLearnersModal').modal('hide');   
                console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Something went wrong: " + error);
            }
		});
	});
	
	//----------------------------------------------------------------------------------------------------
	
	//Delete Learner Account
	$(document).on('click', '.deleteLearnerAccountBtn', function(event){
		event.preventDefault(); //to let the form button works only with Ajax
		var userID=$(this).val();
		
		$.ajax({
			url: './DeleteUserAccount',
			type:'POST',
			cache:false,
			data: {
				userID:userID
				},
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
	
	//---------------------------------------------------------------------------------------------------
	
	//Search for learner accounts by username
	$('#searchForLearnerByUsername').on('input', function(){
		var searchUser = $(this).val();
		var userID =$('#userIDToSearchLearnerByUsername').val();
		
		$.ajax ({
			url: './RetrieveLearnerAccounts',
			type: 'GET',
			cache: false,
			data: {
				searchUser : searchUser,
				userID: userID
				},
			success: function(response)
			{
                $('#learnersTableBodyContainer').html($(response).find('#learnersTableBodyContainer').html());     
			},
			error: function(error)
			{
				console.log("Something went wrong: "+error);
			}
		});
	});
	
	
	//----------------------------------------------------------------------------------------------
	//Edit Profile Details Functionalities
	
	$('#editProfileModal').on('show.bs.modal', function(){
		
		$(this).find('input[type=password]').val('');
	});
	
	//Edite Profile Details Ajax
	$('#editProfileButtonModal').on('click', function(){
		var userID=$('#userIDToEditAccount').val().trim();
		var editedFullName=$('#editedFullName').val().trim();
		var editedUsername=$('#editedUsername').val().trim();
		var editedEmailAddress=$('#editedEmailAddress').val().trim();
		var currentPassword=$('#currentPassword').val().trim();
		var editedPassword=$('#editedPassword').val().trim();
		var editedInterests=$('#editedInterests').val().trim();
		
		
        $.ajax({
			url:'./EditProfileDetails',
			type:'POST',
			cache:false,
			data: {
				userID: userID,
				fullName: editedFullName,
				username:editedUsername,
				emailAddress: editedEmailAddress,
				currentPassword: currentPassword,
				password:editedPassword,
				interests: editedInterests
			},
			success: function(response) 
            { 
				$('#editProfileModal').modal('hide');
				console.log(response);
            }, 
            error: function(error) 
            {
                console.log("Something went wrong: " + error);
            }
		});
        
		
	});
	
	//Ensuring the editedFullName is not whitespaces
	$('#editedFullName').on('input', function(){
		var editedFullName =$(this).val().trim();
		
		if(editedFullName === "")
		{
            $('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
            $('#editProfileButtonModal').prop('disabled', false);
		}
	});
	
	//Ensuring the Current Password is not whitespaces
	$('#currentPassword').on('input', function(){
		var currentPassword =$(this).val().trim();
		
		if(currentPassword === "")
		{
            $('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
            $('#editProfileButtonModal').prop('disabled', false);
		}
	});
	
	//Ensuring the email address is unique and has its specific format
	$('#editedEmailAddress').on('input', function (){
		var editedEmailAddress=$(this).val().trim();
		var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; //this is a used expression to validate the form of an email address
		
		if(editedEmailAddress==="" || !emailPattern.test(editedEmailAddress))
		{
			$('#editProfileButtonModal').prop('disabled', true);
			return;
		}
		
		$.ajax({
			url:'./ValidateEmailAddress',
			type:'POST',
			cache:false,
			data:{emailAddress:editedEmailAddress},
			success:function(response)
			{
				if(response==="available email address")
				{
					$('#editProfileButtonModal').prop('disabled', false);
				}
				else
				{
					$('#editProfileButtonModal').prop('disabled', true);
				}
			},
			error:function(error)
			{
				console.log("Something went wrong: "+error);
			}
		});
		
	});
	
	//Password needs to have at least 8 characters(Numbers, letters, and Symbols)
	$('#editedPassword').on('input',function(){
		var editedPassword=$(this).val().trim();
		var passwordPattern=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&_+])[A-Za-z\d@$!%*?&_+]{8,}$/;
		
		if(editedPassword === "" || !passwordPattern.test(editedPassword))
		{
			$('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
			$('#editProfileButtonModal').prop('disabled', false);
		}
		validateEditedConfirmPassword();
	});
	
	//Both Passwords needs to match
	$('#editedConfirmPassword').on('input',function(){
		validateEditedConfirmPassword();
	});
	
	function validateEditedConfirmPassword()
	{
		var editedConfirmPassword= $('#editedConfirmPassword').val().trim();
		var editedPassword=$('#editedPassword').val().trim();
		
		if(editedConfirmPassword !== editedPassword)
		{
			$('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
			$('#editProfileButtonModal').prop('disabled', false);
		}
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	//Delete User Account Functionality
	$('#deleteAccountButtonModal').on('click',function(){
		var userID= $('#userIDToDeleteAccount').val();
		
		$.ajax({
			url: './DeleteUserAccount',
			type:'POST',
			cache:false,
			data: {userID:userID},
			success: function(response)
			{
				console.log(response);
				
				sessionStorage.clear();
 	    		localStorage.clear();
        		history.replaceState(null, null, 'loginForm.jsp'); // everytime the user clicks on the back button of the browser, he get only the history of the loginForm.jsp
				window.location.href = 'loginForm.jsp';
			},
			error: function (error)
			{
				console.log("Somehting went wrong: " + error);
			}
		});		
	});

	//Log out Functionality
	$('#confirmLogout').click(function(event) {
		event.preventDefault();
		
		sessionStorage.clear();
 	    localStorage.clear();
        history.replaceState(null, null, 'loginForm.jsp'); // everytime the user clicks on the back button of the browser, he get only the history of the loginForm.jsp
		window.location.href = 'loginForm.jsp';  // Redirect to login page
	});
	
	
	
	
	
});