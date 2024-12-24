package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CourseDAO;
import database.ModuleDAO;
import database.QuizDAO;
import database.UserDAO;
import models.Course;
import models.Module;
import models.Quiz;


@WebServlet("/RetrieveModulesAndQuizzes")
public class RetrieveModulesAndQuizzes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModuleDAO moduleDao;
	private CourseDAO courseDao;
	private UserDAO userDao;
	private QuizDAO quizDao;
     
    public RetrieveModulesAndQuizzes() {
        super();
        moduleDao=new ModuleDAO();
        courseDao=new CourseDAO();
        userDao= new UserDAO();
        quizDao= new QuizDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int courseID = Integer.parseInt(request.getParameter("courseID"));
		int userID = Integer.parseInt(request.getParameter("userID"));
		String searchStr = request.getParameter("searchModuleQuiz");
		Course course = courseDao.getCourseByID(courseID);
		
		if(course != null)
		{
			ArrayList<Module> courseModules = new ArrayList<>();
			ArrayList<Quiz> courseQuizzes = new ArrayList<>();
			
			if ((searchStr != null) && !(searchStr.trim().isEmpty()))
			{
				courseModules=moduleDao.searchModule(courseID, searchStr.trim());
				courseQuizzes=quizDao.searchQuiz(courseID, searchStr.trim());
			}
			else
			{
				courseModules=moduleDao.getModulesByCourseID(courseID);
				if (courseModules.isEmpty())
				{
					request.setAttribute("noModules", "Oups! No modules");
				}
				
				courseQuizzes=quizDao.getQuizzesByCourseID(courseID);
				if (courseQuizzes.isEmpty())
				{
					request.setAttribute("noQuizzes", "Oups! No quizzes");
				}
			}			
			request.setAttribute("courseModules", courseModules);
			request.setAttribute("courseQuizzes", courseQuizzes);
			request.setAttribute("course", course);
		}
		else
		{
			request.setAttribute("courseNotFound", "Error");
		}
		
		String role = userDao.getUserByID(userID).getRole();
		String nextPage;
		if (role.equalsIgnoreCase("administrator"))
		{
			nextPage="retrieveModulesAndQuizzesAdministrator.jsp";
		}
		else if (role.equalsIgnoreCase("learner"))
		{
			nextPage="retrieveModulesAndQuizzesLearner.jsp";
		}
		else
		{
			nextPage="loginForm.jsp";
		}
		
		request.setAttribute("userID", userID);
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.include(request, response);
	}
	
	
}
