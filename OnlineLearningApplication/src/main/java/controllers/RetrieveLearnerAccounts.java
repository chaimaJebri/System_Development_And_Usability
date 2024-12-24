package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDAO;
import models.User;


@WebServlet("/RetrieveLearnerAccounts")
public class RetrieveLearnerAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
       
    
    public RetrieveLearnerAccounts() {
        super();
        userDao = new UserDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String searchStr = request.getParameter("searchUser");
		int userID = Integer.parseInt(request.getParameter("userID"));
		ArrayList<User> learnerAccounts = new ArrayList<>();
		
		if((searchStr != null) && !(searchStr.trim().isEmpty()))
		{
			learnerAccounts=userDao.searchLearnerByUsername(searchStr.trim());
		}
		else
		{
			learnerAccounts=userDao.getLearnerAccounts();
		}
		request.setAttribute("userID", userID);
		request.setAttribute("learnerAccounts", learnerAccounts);
		RequestDispatcher dispatcher= request.getRequestDispatcher("retrieveLearnerAccounts.jsp");
		dispatcher.include(request, response);
	}

}
