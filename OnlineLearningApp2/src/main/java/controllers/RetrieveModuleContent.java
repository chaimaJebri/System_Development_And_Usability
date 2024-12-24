package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ModuleDAO;
import models.Module;


@WebServlet("/RetrieveModuleContent")
public class RetrieveModuleContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModuleDAO moduleDao;
	
    public RetrieveModuleContent() {
        super();
        moduleDao= new ModuleDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int moduleID=Integer.parseInt(request.getParameter("moduleID"));
		int courseID=Integer.parseInt(request.getParameter("courseID"));
		int userID=Integer.parseInt(request.getParameter("userID"));
		
		Module module = moduleDao.getModuleByID(moduleID);
		
		if(module != null)
		{
			request.setAttribute("module", module);
		}
		else
		{
			request.setAttribute("moduleNotFound", "Error");		
		}
		request.setAttribute("courseID", courseID);
		request.setAttribute("userID", userID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("retrieveModuleContent.jsp");
		dispatcher.include(request, response);
	}
}
