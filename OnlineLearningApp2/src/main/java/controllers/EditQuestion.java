package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.QuestionDAO;
import models.Question;


@WebServlet("/EditQuestion")
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDAO questionDao;
 
    public EditQuestion() {
        super();
        questionDao= new QuestionDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int questionID= Integer.parseInt(request.getParameter("questionID-edit"));	
		String questionText = request.getParameter("editedQuestionText");
		int quizID = Integer.parseInt(request.getParameter("quizID-edit"));
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if(questionDao.getQuestionByID(questionID) != null)
			{
				if (questionText ==null || questionText.trim().isEmpty()) 
				{
				    out.write("Error");
				    return;
				}
				
				Question question = new Question (questionID, questionText, quizID);
				boolean updated = questionDao.updateQuestion(question);
				if(updated)
				{
					out.print("Success");
				}
				else
				{
					out.print("Failure");
				}
			}
			else
			{
				out.print("Error");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
