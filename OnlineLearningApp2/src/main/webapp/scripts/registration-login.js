$(document).ready(function()
{
	
    //Login Functionalities

	//Ensuring the usernameLogin is not whitespaces
	$('#usernameLogin').on('input', function (){
		var usernameLogin=$(this).val().trim();
		
		if(usernameLogin === "")
		{
            $('#loginButton').prop('disabled', true);
		}
		else
		{
            $('#loginButton').prop('disabled', false);
		}
	});
	
	//Ensuring the passwordLogin is not whitespaces
	$('#passwordLogin').on('input', function (){
		var passwordLogin=$(this).val().trim();
		
		if(passwordLogin === "")
		{
            $('#loginButton').prop('disabled', true);
		}
		else
		{
            $('#loginButton').prop('disabled', false);
		}
	});
	
    //--------------------------------------------------------------------------------------------
    
    //Registration Functionalities

	//Ensuring the fullName is not whitespaces
	$('#fullName').on('input', function(){
		var fullName=$(this).val().trim();
		
		if(fullName === "")
		{
            $('#signUpButton').prop('disabled', true);
		}
		else
		{
            $('#signUpButton').prop('disabled', false);
		}
	});
	
	//Ensuring the username is unique and contains at least 5 characters
	$('#username').on('input', function(){
		var username=$(this).val().trim();
		
		if (username === "")
		{
            $('#signUpButton').prop('disabled', true);
            return;
        }
        
        if (username.length <5)
		{
            $('#signUpButton').prop('disabled', true);
            return;
        }
        
		$.ajax({
			url:'./ValidateUsername',
			type:'POST',
			cache:false,
			data:{username:username},
			success:function(response)
			{
				if(response==="available username")
				{
					$('#signUpButton').prop('disabled', false);
				}
				else
				{
					$('#signUpButton').prop('disabled', true);
				}
			},
			error:function(error)
			{
				console.log("Something went wrong: "+error);
			}
		});
	});
	
	
	//Ensuring the email address is unique and has its specific format
	$('#emailAddress').on('input', function (){
		var emailAddress=$(this).val().trim();
		var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; //this is a used expression to validate the form of an email address
		
		if(emailAddress==="" || !emailPattern.test(emailAddress))
		{
			$('#signUpButton').prop('disabled', true);
			return;
		}
		
		$.ajax({
			url:'./ValidateEmailAddress',
			type:'POST',
			cache:false,
			data:{emailAddress:emailAddress},
			success:function(response)
			{
				if(response==="available email address")
				{
					$('#signUpButton').prop('disabled', false);
				}
				else
				{
					$('#signUpButton').prop('disabled', true);
				}
			},
			error:function(error)
			{
				console.log("Something went wrong: "+error);
			}
		});
		
	});
	
	
	//Password needs to have at least 8 characters(Numbers, letters, and Symbols)
	$('#password').on('input',function(){
		var password=$(this).val().trim();
		var passwordPattern=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&_+])[A-Za-z\d@$!%*?&_+]{8,}$/;
		
		if(password === "" || !passwordPattern.test(password))
		{
			$('#signUpButton').prop('disabled', true);
		}
		else
		{
			$('#signUpButton').prop('disabled', false);
		}
		validateConfirmPassword();
	});
	
	
	//Both Passwords needs to match
	$('#confirmPassword').on('input',function(){
		validateConfirmPassword();
	});
	
	function validateConfirmPassword()
	{
		var confirmPassword= $('#confirmPassword').val().trim();
		var password=$('#password').val().trim();
		
		if(confirmPassword !== password)
		{
			$('#signUpButton').prop('disabled', true);
		}
		else
		{
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
		var interests=$('#interests').val().trim();
		var role=$('#role').val().trim();
		
		
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
	
	
});