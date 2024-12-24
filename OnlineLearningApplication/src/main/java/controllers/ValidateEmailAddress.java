package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDAO;


@WebServlet("/ValidateEmailAddress")
public class ValidateEmailAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
    
    public ValidateEmailAddress() {
        super();
        userDao=new UserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String emailAddress=request.getParameter("emailAddress");
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if (emailAddress ==null || emailAddress.trim().isEmpty()) 
			{
			    out.write("Please provide a valid email address");
			    return;
			}
			
			boolean isUnique=userDao.isUniqueEmailAddress(emailAddress);
			if(isUnique)
			{
				out.write("available email address");
			}
			else
			{
				out.write("unavailable email address");
			}
		}
		catch(IOException e)
		{
			
		}
	}
	//Author: chaimaJebri
}
