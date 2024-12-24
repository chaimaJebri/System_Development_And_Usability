package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDAO;
import models.User;


@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
  
    public UserLogin() {
        super();
        userDao=new UserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username= request.getParameter("usernameLogin").trim();
		String password=request.getParameter("passwordLogin").trim();
		
		User user = userDao.getUserByUsernameAndPassword(username, password);
		
		if(user !=null)
		{
			request.setAttribute("user", user);
			
			String role = user.getRole();
			if("administrator".equalsIgnoreCase(role))
			{
				RequestDispatcher dispatcher= request.getRequestDispatcher("AdministratorPortal");
				dispatcher.include(request, response);
			}
			else if("learner".equalsIgnoreCase(role))
			{
				RequestDispatcher dispatcher= request.getRequestDispatcher("LearnerPortal");
				dispatcher.include(request, response);
			}
		}
		else
		{
			request.setAttribute("errorMessage", "Authentication failed. Please try again.");
			RequestDispatcher dispatcher= request.getRequestDispatcher("loginForm.jsp");
			dispatcher.include(request, response);
		}
	}

	
	//Author: chaimaJebri
}
