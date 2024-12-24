package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import database.OptionDAO;
import database.QuestionDAO;
import models.Option;
import models.Question;

@WebServlet("/AddQuestionOptions")
public class AddQuestionOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDAO questionDao;
	private OptionDAO optionDao;
       
 
    public AddQuestionOptions() {
        super();
        questionDao = new QuestionDAO();
        optionDao= new OptionDAO();
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int quizID= Integer.parseInt(request.getParameter("quizID"));
		String questionText= request.getParameter("questionText");
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if (questionText ==null || questionText.trim().isEmpty()) 
			{
			    out.write("Please provide the question text");
			    return;
			}
			
			Question question = new Question (questionText, quizID);
			int questionID =questionDao.insertQuestion(question);
			
			if (questionID != -1)
			{
				//if the questionID is different to -1 means the question is added successfully in the database
				System.out.println("The questionID: "+questionID);
				
				for (int i = 1; i <= 4; i++) 
			    {
			        String optionText = request.getParameter("optionText" + i);
			        boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect" + i));
			            
			        if(optionText != null && !optionText.trim().isEmpty())
			        {
			        	Option option = new Option (optionText, isCorrect, questionID);
			        	boolean insertedOption = optionDao.insertOption(option);
				        if(insertedOption)
				        {
				        	System.out.println("Option "+i+" added successfully");
				        }
				        else
				        {
				        	System.out.println("Oups! Failed to insert option "+i);
				        }
			        }
			        
			    }
				out.write("The question and its associated options are inserted successfully");
			}
			else
			{
				//if the questionID is -1 means the question is not added in the database
				out.write("Oups! Failed to add question. Please Try Again");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	

}
