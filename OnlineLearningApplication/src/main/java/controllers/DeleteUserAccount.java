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


@WebServlet("/DeleteUserAccount")
public class DeleteUserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
    
    public DeleteUserAccount() {
        super();
        userDao= new UserDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int userID= Integer.parseInt(request.getParameter("userID"));
		String isAdminDeletingLearnerAccount = request.getParameter("isAdminDeletingLearnerAccount");
		
		User user = userDao.getUserByID(userID);
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if (user != null)
			{
				boolean deleted = userDao.deleteUserAccount(userID);
				if (deleted)
				{
					if(isAdminDeletingLearnerAccount != null)
					{
						out.write("Account deleted successfully. The learner's session will end in few seconds.");
					}
					else
					{
						out.write("Account deleted successfully. Your will be logged out in few seconds.");
					}
				}
				else
				{
					out.write("Oups! Failed to delete user account. Please Try Again");
				}
			}
			else
			{
				out.write("User with ID: "+userID+" not found");
			}
		}
		catch (IOException e)
		{
			System.out.print(e.getMessage());
		}
	}
	
	

}
