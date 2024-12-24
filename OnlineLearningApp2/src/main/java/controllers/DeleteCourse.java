package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CourseDAO;


@WebServlet("/DeleteCourse")
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CourseDAO courseDao; 
	
    public DeleteCourse() 
    {
        super();
        courseDao=new CourseDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int courseID= Integer.parseInt(request.getParameter("courseID"));
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if (courseDao.getCourseByID(courseID) !=null)
			{
				boolean deleted = courseDao.deleteCourse(courseID);
				if(deleted)
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
		catch (IOException e) //handling IOException
		{
			e.printStackTrace();
		}
	}
	//Author: chaimaJebri
}
