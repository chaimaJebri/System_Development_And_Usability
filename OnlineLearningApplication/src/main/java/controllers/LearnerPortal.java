package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CourseDAO;
import database.UserDAO;
import models.Course;
import models.User;


@WebServlet("/LearnerPortal")
public class LearnerPortal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	private CourseDAO courseDao;
     
    public LearnerPortal() {
        super();
        userDao= new UserDAO();
        courseDao = new CourseDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User user = (User) request.getAttribute("user");
		if (user == null) { //if the user is null means the request came from the jsp page
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
		String searchStr = request.getParameter("searchCourse");
		ArrayList<Course> courses = new ArrayList<>();
		
		if ((searchStr != null) && !(searchStr.trim().isEmpty()))
		{
			courses=courseDao.searchCourse(searchStr.trim()); //if the searchStr is provided in the request, get the searched courses
		}
		else
		{
			courses=courseDao.getAllCourses();  //retrieve all courses if no param is provided in the request
		}
		
		ArrayList<String> adminEmails = userDao.getAdminEmailAddresses();
		request.setAttribute("adminEmails", adminEmails);
		request.setAttribute("user", user);                //passing all the user details including the id to the jsp file
		request.setAttribute("courses", courses);
		RequestDispatcher dispatcher= request.getRequestDispatcher("learnerPortal.jsp");
		dispatcher.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
