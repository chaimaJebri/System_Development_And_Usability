package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDAO;
import models.User;


@WebServlet("/EditProfileDetails")
public class EditProfileDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
       
   
    public EditProfileDetails() {
        super();
        userDao = new UserDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int userID = Integer.parseInt(request.getParameter("userID"));
		String fullName=request.getParameter("fullName");
		String username=request.getParameter("username");
		String emailAddress=request.getParameter("emailAddress");
		String currentPassword=request.getParameter("currentPassword");
		String newPassword=request.getParameter("password");
		String interests = request.getParameter("interests");
		response.setContentType("text/plain");
		try(PrintWriter out = response.getWriter()){
			User existingUser =userDao.getUserByID(userID);
			if (existingUser != null){
				String role=existingUser.getRole();
				if (currentPassword ==null || currentPassword.trim().isEmpty()) {
				    out.write("For security, Provide your current password");
				    return;
				}
				if (!userDao.verifyPassword(currentPassword, existingUser.getPassword())){  //if the user provided an incorrect current password
					out.write("You have provided an incorrect current password. Please Enter the correct password to edit your profile details");
					return;
				}
				if (fullName ==null || fullName.trim().isEmpty()) {
				    out.write("Please provide your full name");
				    return;
				}
				if (username ==null || username.trim().isEmpty()) {
				    out.write("Please provide a username");
				    return;
				}
				if (emailAddress ==null || emailAddress.trim().isEmpty()) {
				    out.write("Please provide your email address");
				    return;
				}
				if (newPassword ==null || newPassword.trim().isEmpty()){
					newPassword=currentPassword;//if the user didn't provide a new password, the current password will be used as new password
				}
				User user = new User (userID, fullName, username, emailAddress, newPassword, interests, role);
				boolean updated = userDao.updateUserAccount(user);
				if(updated){  
					out.write("Hey "+fullName+"! your account details are updated successfully.");
				}
				else{
					out.write("Oups! Failed to edit account details. Please Try Again");
				}
			}
			else{ //if user not found in the database
				out.write("User with ID: "+userID+", not found");
			}
		}
		catch (IOException e){
			System.out.print(e.getMessage());
		}
	}
}

