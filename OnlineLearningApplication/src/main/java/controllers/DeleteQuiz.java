package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.QuizDAO;


@WebServlet("/DeleteQuiz")
public class DeleteQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizDAO quizDao;
       
 
    public DeleteQuiz() {
        super();
        quizDao = new QuizDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if(quizDao.getQuizByID(quizID) != null)
			{
				boolean deleted=quizDao.deleteQuiz(quizID);
				if(deleted)
				{
					out.write("Quiz with ID: "+quizID+", deleted successfully!");
				}
				else
				{
					out.write("Oups! Failed to delete Quiz with ID: "+quizID+". Please Try Again");
				}
			}
			else
			{
				out.write("Quiz with ID: "+ quizID+" not found");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
