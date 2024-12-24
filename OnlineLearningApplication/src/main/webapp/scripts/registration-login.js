$(document).ready(function()
{
	
    //Login Functionalities

	//Ensuring the usernameLogin is not whitespaces
	$('#usernameLogin').on('input', function (){
		var usernameLogin=$(this).val().trim();
		
		if(usernameLogin === "")
		{
			$('#validateUsernameLogin').text('Please provide the username').css("color", "red");
            $('#loginButton').prop('disabled', true);
		}
		else
		{
			$('#validateUsernameLogin').text('');
            $('#loginButton').prop('disabled', false);
		}
	});
	
	//Ensuring the passwordLogin is not whitespaces
	$('#passwordLogin').on('input', function (){
		var passwordLogin=$(this).val().trim();
		
		if(passwordLogin === "")
		{
			$('#validatePasswordLogin').text('Please provide the password').css("color", "red");
            $('#loginButton').prop('disabled', true);
		}
		else
		{
			$('#validatePasswordLogin').text('');
            $('#loginButton').prop('disabled', false);
		}
	});
	
    //--------------------------------------------------------------------------------------------
    
    //Registration Functionalities

	//Full name validation
	$('#fullName').on('input', function(){
		var fullName=$(this).val().trim();
		
		if(fullName === "")    //if the input is empty or white spaces return an error message and disable the sign up button
		{
			$('#validateFullName').text('Please provide your full name').css("color", "red");
            $('#signUpButton').prop('disabled', true);
		}
		else   //otherwise enable the sign up button
		{
			$('#validateFullName').text('');
            $('#signUpButton').prop('disabled', false);
		}
	});
	
	//Username validation
	$('#username').on('input', function(){
		var username=$(this).val().trim();
		
		if (username === "")  //if the input is empty or white spaces return an error message and disable the sign up button
		{
            $('#validateUsername').text('Please provide a valid username').css("color", "red");
            $('#signUpButton').prop('disabled', true);
            return;
        }
        
        if (username.length <5)     //if the username is less than 5 characters return an error message and disable the sign up button
		{
            $('#validateUsername').text('Minimum 5 characters are required.').css("color", "red");
            $('#signUpButton').prop('disabled', true);
            return;
        }
        //if the username is not whitespaces or less than 5 characters, send it to the server to check if it is unique or not
		$.ajax({
			url:'./ValidateUsername',
			type:'POST',
			cache:false,
			data:{username:username},
			success:function(response)
			{
				if(response==="available username")       //if the username is unique return a success message and enable the sign up button
				{
					$('#validateUsername').text('Great Choice! You can use this username.').css("color", "green");
					$('#signUpButton').prop('disabled', false);
				}
				else   //otherwise disable the sign up button
				{
					$('#validateUsername').text('Oups! This username is unavailable. Try another.').css("color", "red");
					$('#signUpButton').prop('disabled', true);
				}
			},
			error:function(error)
			{
				console.log("Error occured while typing username: "+error);
			}
		});
	});
	
	
	//Email address validation
	$('#emailAddress').on('input', function (){
		var emailAddress=$(this).val().trim();
		var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; //this is a used expression to validate the form of an email address
		
		if(emailAddress==="" || !emailPattern.test(emailAddress))   //if the input is empty or whitespaces or it doesn't follow the format of an email address
		{                                                           //return an error message and disable the sign up button
			$('#validateEmailAddress').text('Please provide a valid email address').css("color", "red");
			$('#signUpButton').prop('disabled', true);
			return;
		}
		//if the email address is not empty and follows the regular expression pattern, send it to the server to check if it is unique or not
		$.ajax({
			url:'./ValidateEmailAddress',
			type:'POST',
			cache:false,
			data:{emailAddress:emailAddress},
			success:function(response)
			{
				if(response==="available email address")   //if the email address is unique return a success message  and enable the sign up button
				{
					$('#validateEmailAddress').text('Great! You can use this email address.').css("color", "green");
					$('#signUpButton').prop('disabled', false);
				}
				else        //otherwise return an error message and disable the sign up button
				{
					$('#validateEmailAddress').text('Oups! This email address is unavailable. Try another.').css("color", "red");
					$('#signUpButton').prop('disabled', true);
				}
			},
			error:function(error)
			{
				console.log("Error occured while typing email address: "+error);
			}
		});
		
	});
	
	
	//Password needs to have at least 8 characters(Uppercase & lowercase letters, numbers, and Symbols)
	$('#password').on('input',function(){
		var password=$(this).val().trim();
		var passwordPattern=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&_+])[A-Za-z\d@$!%*?&_+]{8,}$/;  //regex to check if the password has at least 8 characters
		                                                                                        //including uppercase and lowercase letters, numbers and special characters
		if(password === "" || !passwordPattern.test(password))   //if the pwd is empty or doesn't follow the regex pattern return error message and disable the sign up button
		{
			$('#validatePassword').text('Minimum 8 characters including letters, numbers, & symbols are required').css("color", "red");
			$('#signUpButton').prop('disabled', true);
		}
		else             //otherwise return a success message and enable the sign up button
		{
			$('#validatePassword').text('Great Choice! You can use this password.').css("color", "green");
			$('#signUpButton').prop('disabled', false);
		}
		validateConfirmPassword();
	});
	
	$('#confirmPassword').on('input',function(){
		validateConfirmPassword();
	});
	
	//Check if the password and confirm password input fields match or not
	function validateConfirmPassword()
	{
		var confirmPassword= $('#confirmPassword').val().trim();
		var password=$('#password').val().trim();
		
		if(confirmPassword !== password)   //if they don't match return an error and disable the sign up button
		{
			$('#validateConfirmPassword').text('Passwords do not match').css("color", "red");
			$('#signUpButton').prop('disabled', true);
		}
		else            //otherwise return a success message and enable the sign up button
		{
			$('#validateConfirmPassword').text('Passwords match').css("color", "green");
			$('#signUpButton').prop('disabled', false);
		}
	}
	
	//Create User Account Ajax
	$('#signUpButton').on('click', function(event){
		event.preventDefault(); //to let the sign up button works only with Ajax	
		var fullName=$('#fullName').val().trim();
		var username=$('#username').val().trim();
		var emailAddress=$('#emailAddress').val().trim();
		var password=$('#password').val().trim();
		var confirmPassword=$('#confirmPassword').val().trim();
		var interests=$('#interests').val().trim();
		var role=$('#role').val().trim();
		isValid=true;
		$('#isCreated').text('');
		if (fullName === ""){ 
            $('#validateFullName').text('Please provide your full name !').css("color", "red");
            isValid=false;
        }
        if (username === ""){
            $('#validateUsername').text('Please provide a username !').css("color", "red");
            isValid=false;
        }
        if (emailAddress === ""){
            $('#validateEmailAddress').text('Please provide your email address !').css("color", "red");
            isValid=false;
        }
        if (password === ""){
            $('#validatePassword').text('Please provide a password !').css("color", "red");
            isValid=false;
        }
        if (confirmPassword === ""){
            $('#validateConfirmPassword').text('Please confirm you password !').css("color", "red");
            isValid=false;
        }
        if(!isValid){
			return;
		}	
        $.ajax({
			url:'./CreateUserAccount',
			type:'POST',
			cache:false,
			data: {
				fullName: fullName,
				username:username,
				emailAddress: emailAddress,
				password: password,
				interests: interests,
				role: role
			},
			success: function(response){   //if the account is created successfully then return a success message and clear the form inputs
				if(response==="success"){
					$('#isCreated').text("Account Created Successfully. Unlock a world of possibilities by logging in.").css("color","green");
					
					$('#fullName').val('');
					$('#validateFullName').text('');
					$('#username').val('');
					$('#validateUsername').text('');
					$('#emailAddress').val('');
					$('#validateEmailAddress').text('');
					$('#password').val('');
					$('#validatePassword').text('');
					$('#confirmPassword').val('');
					$('#validateConfirmPassword').text('');
					$('#interests').val('');
				}
				else{           //otherwise return an error message
					$('#isCreated').text("Oups! Failed to create account. Please Try Again").css("color","red");
				}
            }, 
            error: function(error) {
                console.log("Error occurred while creating an account: " + error);
            }
		});
	});
	
	
});