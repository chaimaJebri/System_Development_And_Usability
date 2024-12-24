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

@WebServlet("/CreateUserAccount")
public class CreateUserAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
       
    
    public CreateUserAccount() {
        super();
        userDao=new UserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String fullName=request.getParameter("fullName");
		String username=request.getParameter("username");
		String emailAddress=request.getParameter("emailAddress");
		String password=request.getParameter("password");
		String interests = request.getParameter("interests");
		String role = request.getParameter("role");
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
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
			if (password ==null || password.trim().isEmpty()) 
			{
			    out.write("Error");
			    return;
			}
			User user = new User (fullName, username, emailAddress, password, interests, role);
			boolean created = userDao.createUserAccount(user);
			if (created)
			{
				out.write("success");
			}
			else
			{
				out.write("Failure");
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	

}
