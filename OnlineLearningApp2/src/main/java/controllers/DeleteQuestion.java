package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.QuestionDAO;


@WebServlet("/DeleteQuestion")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDAO questionDao;
       
    public DeleteQuestion() {
        super();
        questionDao = new QuestionDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int questionID = Integer.parseInt(request.getParameter("questionID"));
		response.setContentType("text/plain");
		
		try (PrintWriter out = response.getWriter())
		{
			if (questionDao.getQuestionByID(questionID) != null)
			{
				boolean deleted = questionDao.deleteQuestion(questionID);
				if (deleted)
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
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}