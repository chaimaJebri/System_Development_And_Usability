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


@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDAO courseDao;
    
    public EditCourse() {
        super();
        courseDao=new CourseDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int courseID = Integer.parseInt(request.getParameter("courseID-edit"));
		String courseName=request.getParameter("editedCourseName");
		String description =request.getParameter("editedDescription");
		response.setContentType("text/plain");
		
		try (PrintWriter out = response.getWriter())
		{
			if (courseDao.getCourseByID(courseID) != null)
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
				
				Course course = new Course (courseID, courseName, description);
				boolean edited = courseDao.updateCourse(course);
				if (edited)
				{
					out.print("Course with ID: "+courseID+", edited successfully");
				}
				else
				{
					out.print("Oups! Failed to edit course with ID: "+courseID+ ". Please Try Again");
				}
			}
			else
			{
				out.print("Course with ID: "+courseID+", not found");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		//Author: chaimaJebri
	}

}
