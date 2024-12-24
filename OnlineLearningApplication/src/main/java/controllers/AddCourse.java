package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CourseDAO;
import models.Course;


@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDAO courseDao;
    
    public AddCourse() {
        super();
        courseDao=new CourseDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String courseName=request.getParameter("addedCourseName");
		String description =request.getParameter("addedDescription");
		response.setContentType("text/plain");
		try(PrintWriter out = response.getWriter())
		{
			if (courseName ==null || courseName.trim().isEmpty()) 
			{
			    out.write("Please provide valid name for the course");
			    return;
			}
			
			if (description ==null || description.trim().isEmpty())
			{
				out.write("Please provide valid description for the course");
				return;
			}
			
			Course course = new Course(courseName, description);
			boolean inserted = courseDao.insertCourse(course);
			if (inserted)
			{
				out.write("Course with name: "+courseName+", added successfully.");
			}
			else
			{
				out.write("Oups! Failed to add course. Please Try Again");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
