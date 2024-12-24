$(document).ready(function()
{
	//Edit FAQs
	$('#editFAQsBtn').on('click', function(event){
		event.preventDefault();
		
		var editedFAQs=$('#editedFAQs').val().trim();
		var isValid=true;
		
		$('#validateEditedFAQs').text('');
		
		if (editedFAQs === "")
		{
            $('#validateEditedFAQs').text('Please provide the FAQs').css("color", "red");
            isValid=false;
        }
        if(!isValid)
        {
			return;
		}
		
        $.ajax({
			url:'./FAQsHandler',
			type:'POST',
			cache:false,
			data: {
				editedFAQs: editedFAQs
			},
			success: function(response) 
            { 
				sendNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while updating the FAQs: " + error);
            }
		});
		
	});
	
	
	
	//-------------------------------------------------------------------------------------------------
	
	//Edit the system's terms and policies
	$('#editTermsPoliciesBtn').on('click', function(event){
		event.preventDefault();
		
		var editedTermsPolicies=$('#editedTermsPolicies').val().trim();
		var isValid=true;
		
		$('#validateEditedTermsPolicies').text('');
		
		if (editedTermsPolicies === "")
		{
            $('#validateEditedTermsPolicies').text('Please provide the system terms and policies').css("color", "red");
            isValid=false;
        }
        if(!isValid)
        {
			return;
		}
		
        $.ajax({
			url:'./TermsAndPoliciesHandler',
			type:'POST',
			cache:false,
			data: {
				editedTermsPolicies: editedTermsPolicies
			},
			success: function(response) 
            { 
				sendNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while updating system's terms and policies: " + error);
            }
		});
		
	});
	
	
	//---------------------------------------------------------------------------------------------------
	
	//Select all learners functionality
	$('#select-all-learners').on('change', function(){
		$('.a-learner').prop('checked', this.checked);
	});
	
	//if a single checkbox if checked or uncked, the number of checkboxes checked will be calculated, if all checkboxes are checked the select all will be checked as well, otherwise it will be unchecked
	$(document).on('change', '.a-learner', function () {
        if ($('.a-learner:checked').length === $('.a-learner').length) 
        {
            $('#select-all-learners').prop('checked', true);
        } 
        else 
        {
            $('#select-all-learners').prop('checked', false);
        }
    });
	
	
	//-----------------------------------------------------------------------------------------------
	
	$('#sendEmailBtn').on('click', function(event){
		
		event.preventDefault(); //to let the form button works only with Ajax
		$('#notifyLearnersModal').find('input[type=text]').val(''); //clear the modal inputs for next use
		$('#emailSubjectError').text('');      //clear the error containers
		$('#emailTextError').text('');
		$('#notifyLearnersModal').modal('show');
	});
	
	$('#sendEmailBtnModal').on('click', function(){
		
		var formData= $('#learnersTableForm').serializeArray();
		var emailSubject= $('#emailSubject').val().trim();
		var emailText= $('#emailText').val().trim();
		var isValid=true;
		
		formData.push({ name: "emailSubject", value: emailSubject });
		formData.push({ name: "emailText", value: emailText });
		
		$('#emailSubjectError').text('');
		$('#emailTextError').text('');
		
		if (emailSubject === "")
		{
            $('#emailSubjectError').text('Please provide the email subject');
            isValid=false;
        }
		
		if (emailText === "")
		{
            $('#emailTextError').text('Please provide the email text');
            isValid=false;
        }
        
        if(!isValid)
        {
			return;
		}
		
		$.ajax({
			url:'./EmailNotificationsAPI',
			type:'POST',
			cache:false,
			data: formData,
			success: function(response) 
            { 
				$('#notifyLearnersModal').modal('hide');   
                sendNotification(response);    
            }, 
            error: function(error) 
            {
                console.log("Error occurred while sending email to learners: " + error);
            }
		});
	});
	
	//----------------------------------------------------------------------------------------------------
	
	//Delete Learner Account
	$(document).on('click', '.deleteLearnerAccountBtn', function(event){
		event.preventDefault(); //to let the form button works only with Ajax
		var userID=$(this).val();
		$('#deleteLearnerAccountMdl').data('userID',userID);
		$('#deleteLearnerAccountMdl').modal('show');
	});
	
	$('#deleteLearnerAccountBtnMdl').on('click',function(){ 
		var userID=$('#deleteLearnerAccountMdl').data('userID');
		
		$.ajax({
			url: './DeleteUserAccount',
			type:'POST',
			cache:false,
			data: {
				userID:userID,
				isAdminDeletingLearnerAccount: "yes"
				},
			success: function(response)
			{
				$('#deleteLearnerAccountMdl').modal('hide');
				sendNotification(response);
			},
			error: function (error)
			{
				console.log("Error occurred while deleting learner account: " + error);
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
				console.log("Error occured while searching learners: "+error);
			}
		});
	});
	
	
	//----------------------------------------------------------------------------------------------
	//Edit Profile Details Functionalities
	
	//Function responsible for handling notifications
	function sendNotification (message)
	{
		var toastNotifications = new bootstrap.Toast(document.getElementById('toastNotifications'),{ //call the toastNotifications created in the jsp file
			autohide: true, //if the user didn't close the toast, it will appear after 3s
			delay: 3000
		});
		
		$('#toastNotifications .toast-body').text(message); //put the message I want to show in the toast body then display the toast
		toastNotifications.show();
		
		toastNotifications._element.addEventListener('hidden.bs.toast',function(){ 
			location.reload();
		});
	}
	
	$('#editProfileModal').on('show.bs.modal', function(){
		
		$('#validateEditedFullName').text('');
		$('#validateEditedEmailAddress').text('');
		$('#validateCurrentPassword').text('');
		$('#validateEditedPassword').text('');
		$('#validateEditedConfirmPassword').text('');
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
		isValid=true;
		
			
		if (editedFullName === "")
		{
            $('#validateEditedFullName').text('Please provide your full name !').css("color", "red");
            isValid=false;
        }
        if (editedUsername === "")
		{
            $('#validateEditedUsername').text('Please provide a username !').css("color", "red");
            isValid=false;
        }
        if (editedEmailAddress === "")
		{
            $('#validateEditedEmailAddress').text('Please provide your email address !').css("color", "red");
            isValid=false;
        }
        if (currentPassword === "")
		{
            $('#validateCurrentPassword').text('To edit your profile details, enter your current password!').css("color", "red");
            isValid=false;
        }
        
        if(!isValid)
        {
			return;
		}
		
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
				sendNotification(response);
            }, 
            error: function(error) 
            {
                console.log("Error occurred while editing profile details: " + error);
            }
		});
        
		
	});
	
	//Ensuring the editedFullName is not whitespaces
	$('#editedFullName').on('input', function(){
		var editedFullName =$(this).val().trim();
		
		if(editedFullName === "")
		{
			$('#validateEditedFullName').text('Please provide your full name').css("color", "red");
            $('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
			$('#validateEditedFullName').text('');
            $('#editProfileButtonModal').prop('disabled', false);
		}
	});
	
	//Ensuring the Current Password is not whitespaces
	$('#currentPassword').on('input', function(){
		var currentPassword =$(this).val().trim();
		
		if(currentPassword === "")
		{
			$('#validateCurrentPassword').text('To edit your profile details, enter your current password.').css("color", "red");
            $('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
			$('#validateCurrentPassword').text('');
            $('#editProfileButtonModal').prop('disabled', false);
		}
	});
	
	//Ensuring the email address is unique and has its specific format
	$('#editedEmailAddress').on('input', function (){
		var editedEmailAddress=$(this).val().trim();
		var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; //this is a used expression to validate the form of an email address
		
		if(editedEmailAddress==="" || !emailPattern.test(editedEmailAddress))
		{
			$('#validateEditedEmailAddress').text('Please provide a valid email address').css("color", "red");
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
					$('#validateEditedEmailAddress').text('Great! You can use this email address.').css("color", "green");
					$('#editProfileButtonModal').prop('disabled', false);
				}
				else
				{
					$('#validateEditedEmailAddress').text('Oups! This email address is unavailable. Try another.').css("color", "red");
					$('#editProfileButtonModal').prop('disabled', true);
				}
			},
			error:function(error)
			{
				console.log("Error occured while typing email address: "+error);
			}
		});
		
	});
	
	//Password needs to have at least 8 characters(Numbers, letters, and Symbols)
	$('#editedPassword').on('input',function(){
		var editedPassword=$(this).val().trim();
		var passwordPattern=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&_+])[A-Za-z\d@$!%*?&_+]{8,}$/;
		
		if(editedPassword === "" || !passwordPattern.test(editedPassword))
		{
			$('#validateEditedPassword').text('Minimum 8 characters including letters, numbers, & symbols are required').css("color", "red");
			$('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
			$('#validateEditedPassword').text('Great Choice! You can use this password.').css("color", "green");
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
			$('#validateEditedConfirmPassword').text('Passwords do not match').css("color", "red");
			$('#editProfileButtonModal').prop('disabled', true);
		}
		else
		{
			$('#validateEditedConfirmPassword').text('Passwords match').css("color", "green");
			$('#editProfileButtonModal').prop('disabled', false);
		}
	}
	
	//Function responsible for handling delete account notifications
	function DeleteAccountNotification (message)
	{
		var toastNotifications = new bootstrap.Toast(document.getElementById('toastNotifications'),{ //call the toastNotifications created in the jsp file
			autohide: true, //if the user didn't close the toast, it will appear after 3s
			delay: 3000
		});
		
		$('#toastNotifications .toast-body').text(message); //put the message I want to show in the toast body then display the toast
		toastNotifications.show();
		
		toastNotifications._element.addEventListener('hidden.bs.toast',function(){ 
				sessionStorage.clear();
 	    		localStorage.clear();
        		history.replaceState(null, null, 'loginForm.jsp'); // everytime the user clicks on the back button of the browser, he get only the history of the loginForm.jsp
				window.location.href = 'loginForm.jsp';
			
		});
	}
	
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
				$('#deleteAccountModal').modal('hide');
				DeleteAccountNotification(response);
			},
			error: function (error)
			{
				console.log("Error occurred while deleting user account: " + error);
			}
		});		
	});

	//Log out Functionality
	$('#confirmLogout').click(function() {
		sessionStorage.clear();
 	    localStorage.clear();
        history.replaceState(null, null, 'loginForm.jsp'); // everytime the user clicks on the back button of the browser, he get only the history of the loginForm.jsp
		window.location.href = 'loginForm.jsp';  // Redirect to login page
	});
	
	
	
	
	
});