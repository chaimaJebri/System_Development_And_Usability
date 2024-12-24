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


@WebServlet("/EditQuiz")
public class EditQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizDAO quizDao;
      
    public EditQuiz() {
        super();
        quizDao = new QuizDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int quizID= Integer.parseInt(request.getParameter("quizID-edit"));
		int courseID= Integer.parseInt(request.getParameter("editQuizCourseID"));
		String quizName= request.getParameter("editedQuizName");
		String quizOverview = request.getParameter("editedQuizOverview");
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if (quizDao.getQuizByID(quizID) != null)
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
				
				Quiz quiz = new Quiz (quizID, quizName, quizOverview, courseID);
				boolean edited = quizDao.updateQuiz(quiz);
				if(edited)
				{
					out.print("Quiz with ID: "+quizID+", edited successfully");
				}
				else
				{
					out.print("Oups! Failed to edit quiz with ID: "+quizID+ ". Please Try Again");
				}
			}
			else
			{
				out.print("Quiz with ID: "+quizID+", not found");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		//Author: chaimaJebri
	}

}
