package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.OptionDAO;
import database.QuestionDAO;
import models.Option;
import models.Question;


@WebServlet("/QuizResult")
public class QuizResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDAO questionDao;
	private OptionDAO optionDao;
       
    public QuizResult() {
        super();
        questionDao = new QuestionDAO(); 
        optionDao = new OptionDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int quizID=Integer.parseInt(request.getParameter("quizID"));
		int courseID=Integer.parseInt(request.getParameter("courseID"));
		int userID=Integer.parseInt(request.getParameter("userID"));
		
		ArrayList<Question> quizQuestions = questionDao.getQuestionsByQuizID(quizID);
		int numQuestions = quizQuestions.size();
		int quizScore=0;
		
		for (Question question: quizQuestions)
		{
			String selectedOptionStr = request.getParameter("answerForQuestion"+question.getQuestionID());
			if(selectedOptionStr != null)
			{
				int selectedOptionID=Integer.parseInt(selectedOptionStr);
				Option option = optionDao.getOptionByID(selectedOptionID);
				if(option.getIsCorrect())
				{
					quizScore++;
				}
			}
		}
		
		int passingScore = numQuestions/2;
		if(quizScore>= passingScore)
		{
			request.setAttribute("pass", "You correctly answered "+quizScore+" out of "+numQuestions+" questions.");
		}
		else
		{
			request.setAttribute("fail", "From "+numQuestions+" questions, "+quizScore+" are answered correctly. You must study much harder!");
		}
		
		request.setAttribute("courseID", courseID);
		request.setAttribute("userID", userID);
		request.setAttribute("quizID", quizID);
		RequestDispatcher dispatcher= request.getRequestDispatcher("quizResult.jsp");
		dispatcher.include(request, response);
	}
	//Author: chaimaJebri
	
}