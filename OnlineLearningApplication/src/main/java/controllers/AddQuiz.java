package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.QuizDAO;
import models.Quiz;


@WebServlet("/AddQuiz")
public class AddQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizDAO quizDao;
       
    public AddQuiz() {
        super();
        quizDao= new QuizDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int courseID= Integer.parseInt(request.getParameter("addQuizCourseID"));
		String quizName=request.getParameter("addedQuizName");
		String quizOverview= request.getParameter("addedQuizOverview");
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if (quizName ==null || quizName.trim().isEmpty()) 
			{
			    out.write("Please provide valid name for the quiz");
			    return;
			}
			if (quizOverview ==null || quizOverview.trim().isEmpty()) 
			{
			    out.write("Please provide valid overview for the quiz");
			    return;
			}
			
			Quiz quiz = new Quiz (quizName, quizOverview, courseID);
			boolean inserted=quizDao.insertQuiz(quiz);
			if (inserted)
			{
				out.print("Quiz with Name: "+quizName+" added successfully");
			}
			else
			{
				out.print("Oups! Failed to add quiz. Please Try Again");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	
	
}
