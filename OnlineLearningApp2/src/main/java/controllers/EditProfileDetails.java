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
		
		try(PrintWriter out = response.getWriter())
		{
			User existingUser =userDao.getUserByID(userID);
			if (existingUser != null)
			{
				String role=existingUser.getRole();
				
				if (currentPassword ==null || currentPassword.trim().isEmpty()) 
				{
				    out.write("Error");
				    return;
				}
				if (!userDao.verifyPassword(currentPassword, existingUser.getPassword()))
				{
					out.write("Error");
					return;
				}
				if (fullName ==null || fullName.trim().isEmpty()) 
				{
				    out.write("Error");
				    return;
				}
				if (username ==null || username.trim().isEmpty()) 
				{
				    out.write("Error");
				    return;
				}
				if (emailAddress ==null || emailAddress.trim().isEmpty()) 
				{
				    out.write("Error");
				    return;
				}
				if (newPassword ==null || newPassword.trim().isEmpty()) //the new password
				{
					newPassword=currentPassword;//if the user didn't provide a new password, the current password will be used as new password
				}
				
				User user = new User (userID, fullName, username, emailAddress, newPassword, interests, role);
				boolean updated = userDao.updateUserAccount(user);
				if(updated)
				{
					out.write("Success");
				}
				else
				{
					out.write("Failure");
				}
			}
			else
			{
				out.write("Error");
			}
		}
		catch (IOException e)
		{
			System.out.print(e.getMessage());
		}
	}
	
	
	

}
