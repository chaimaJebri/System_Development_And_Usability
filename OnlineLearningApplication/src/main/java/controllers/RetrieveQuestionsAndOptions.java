package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.OptionDAO;
import database.QuestionDAO;
import database.QuizDAO;
import database.UserDAO;
import models.Option;
import models.Question;
import models.Quiz;


@WebServlet("/RetrieveQuestionsAndOptions")
public class RetrieveQuestionsAndOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizDAO quizDao;
	private QuestionDAO questionDao;
	private OptionDAO optionDao;
	private UserDAO userDao;
       
   
    public RetrieveQuestionsAndOptions() {
        super();
        quizDao= new QuizDAO();
        questionDao = new QuestionDAO();
        optionDao = new OptionDAO();
        userDao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int quizID= Integer.parseInt(request.getParameter("quizID"));
		int courseID= Integer.parseInt(request.getParameter("courseID"));
		int userID= Integer.parseInt(request.getParameter("userID"));
		String displayAnswers = request.getParameter("displayAnswers");
		
		Quiz quiz = quizDao.getQuizByID(quizID);
		if(quiz != null)
		{
			ArrayList<Question> questions = questionDao.getQuestionsByQuizID(quizID);
			if (questions.isEmpty())
			{
				request.setAttribute("noQuestions", "Oups! No Questions Have been Added Yet");
			}
			
			HashMap<Integer, ArrayList<Option>> options = new HashMap<>();
			
			for (Question question : questions)
			{
				ArrayList<Option> questionOptions = optionDao.getOptionsByQuestionID(question.getQuestionID());
				options.put(question.getQuestionID(), questionOptions);
			}
			request.setAttribute("quiz", quiz);
			request.setAttribute("questions", questions);
			request.setAttribute("options", options);
		}
		else
		{
			request.setAttribute("quizNotFound", "Oups! Quiz with ID: '"+quizID+"' not found.");
		}
		
		String role = userDao.getUserByID(userID).getRole();
		String nextPage;
		if (role.equalsIgnoreCase("administrator"))
		{
			nextPage="retrieveQuestionsAndOptionsAdministrator.jsp";
		}
		else if (role.equalsIgnoreCase("learner"))
		{
			if(displayAnswers != null)
			{
				nextPage="quizAnswers.jsp";   //to check the quiz correct answers
			}
			else
			{
				nextPage="retrieveQuestionsAndOptionsLearner.jsp";   //to take the quiz
			}
		}
		else
		{
			nextPage="loginForm.jsp";
		}
		
		request.setAttribute("courseID", courseID);
		request.setAttribute("userID", userID);
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.include(request, response);
	}
	
	
}
