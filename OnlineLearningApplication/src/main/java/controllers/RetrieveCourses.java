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
import models.Course;


@WebServlet("/RetrieveCourses")
public class RetrieveCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDAO courseDao;
       
    
    public RetrieveCourses() {
        super();
        courseDao = new CourseDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String searchStr = request.getParameter("searchCourse");
		int userID=Integer.parseInt(request.getParameter("userID"));
		ArrayList<Course> courses = new ArrayList<>();
		
		if ((searchStr != null) && !(searchStr.trim().isEmpty()))
		{
			courses=courseDao.searchCourse(searchStr.trim()); //if the searchStr is provided in the request, get the searched courses
		}
		else
		{
			courses=courseDao.getAllCourses();  //retrieve all courses if no param is provided in the request
		}
		request.setAttribute("userID", userID);
		request.setAttribute("courses", courses);
		RequestDispatcher dispatcher= request.getRequestDispatcher("retrieveCourses.jsp");
		dispatcher.include(request, response);
	}
}