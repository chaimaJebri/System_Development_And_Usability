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


@WebServlet("/AdministratorPortal")
public class AdministratorPortal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
     
    public AdministratorPortal() {
        super();
        userDao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User user = (User) request.getAttribute("user");
		
		if (user == null) { //if the user null means the request came from the jsp page
			String userIDParam = request.getParameter("userID");
	        if (userIDParam != null) 
	        {
	            user = userDao.getUserByID(Integer.parseInt(userIDParam));
	        }
        }
		
		if (user == null) {//if the user is still null after looking at the database means maybe the account is already deleted but the page is not refreshed
            response.sendRedirect("loginForm.jsp");
            return;
        }
		request.setAttribute("user", user);
		RequestDispatcher dispatcher= request.getRequestDispatcher("administratorPortal.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
