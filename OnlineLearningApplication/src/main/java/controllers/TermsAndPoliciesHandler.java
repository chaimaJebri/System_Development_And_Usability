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


@WebServlet("/TermsAndPoliciesHandler")
public class TermsAndPoliciesHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;

    public TermsAndPoliciesHandler() {
        super();
        userDao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int userID = Integer.parseInt(request.getParameter("userID"));
		StringBuilder termsAndPolicies = new StringBuilder();
		String filePath=getServletContext().getRealPath("/WEB-INF/terms_policies.txt");
		
		try(BufferedReader reader = new BufferedReader (new FileReader(filePath)))
		{
			String line;
			while((line = reader.readLine())!=null)
			{
				termsAndPolicies.append(line).append("\n");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("userID", userID);
		request.setAttribute("termsAndPolicies", termsAndPolicies.toString());
		String nextPage;
		
		if(userID == 0)
		{
			nextPage="termsAndPoliciesRegistration.jsp";
		}
		else
		{
			String role = userDao.getUserByID(userID).getRole();
			if (role.equalsIgnoreCase("administrator"))
			{
				nextPage="termsAndPoliciesAdministrator.jsp";
			}
			else if (role.equalsIgnoreCase("learner"))
			{
				nextPage="termsAndPoliciesLearner.jsp";
			}
			else
			{
				nextPage="loginForm.jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String editedTermsPolicies = request.getParameter("editedTermsPolicies");
		String filePath=getServletContext().getRealPath("/WEB-INF/terms_policies.txt");
		
		try (PrintWriter out = response.getWriter())
		{
			if (editedTermsPolicies ==null || editedTermsPolicies.trim().isEmpty()) 
			{
			    out.write("Please provide the system's terms and policies.");
			    return;
			}
			
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
			{
				writer.write(editedTermsPolicies);
				out.write("System's terms and policies updated successfully");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	

}
