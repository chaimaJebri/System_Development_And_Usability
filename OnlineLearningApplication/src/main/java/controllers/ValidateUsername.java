package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDAO;


@WebServlet("/ValidateUsername")
public class ValidateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
       
  
    public ValidateUsername() {
        super();
        userDao= new UserDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=request.getParameter("username");
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if (username ==null || username.trim().isEmpty()) 
			{
			    out.write("Please provide a valid username");
			    return;
			}
			
			boolean isUnique=userDao.isUniqueUsername(username);
			if (isUnique)
			{
				out.write("available username");
			}
			else
			{
				out.write("unavailable username");
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

}
