package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDAO;

@WebServlet("/FAQsHandler")
public class FAQsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
      
    public FAQsHandler() {
        super();
        userDao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int userID = Integer.parseInt(request.getParameter("userID"));
		StringBuilder FAQs = new StringBuilder();
		String filePath=getServletContext().getRealPath("/WEB-INF/FAQs.txt");
		
		try(BufferedReader reader = new BufferedReader (new FileReader(filePath)))
		{
			String line;
			while((line = reader.readLine())!=null)
			{
				FAQs.append(line).append("\n");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("userID", userID);
		request.setAttribute("FAQs", FAQs.toString());
		
		String role = userDao.getUserByID(userID).getRole();
		String nextPage;
		if (role.equalsIgnoreCase("administrator"))
		{
			nextPage="FAQsAdministrator.jsp";
		}
		else if (role.equalsIgnoreCase("learner"))
		{
			nextPage="FAQsLearner.jsp";
		}
		else
		{
			nextPage="loginForm.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String editedFAQs = request.getParameter("editedFAQs");
		String filePath=getServletContext().getRealPath("/WEB-INF/FAQs.txt");
		
		try (PrintWriter out = response.getWriter())
		{
			if (editedFAQs ==null || editedFAQs.trim().isEmpty()) 
			{
			    out.write("Please provide the FAQs.");
			    return;
			}
			
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
			{
				writer.write(editedFAQs);
				out.write("FAQs updated successfully");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

}
