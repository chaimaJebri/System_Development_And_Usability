package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ModuleDAO;


@WebServlet("/DeleteModule")
public class DeleteModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModuleDAO moduleDao;
       
    
    public DeleteModule() {
        super();
        moduleDao=new ModuleDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int moduleID = Integer.parseInt(request.getParameter("moduleID"));
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if(moduleDao.getModuleByID(moduleID)!= null)
			{
				boolean deleted=moduleDao.deleteModule(moduleID);
				if(deleted)
				{
					out.write("Module with ID: "+moduleID+", deleted successfully!");
				}
				else
				{
					out.write("Oups! Failed to delete module with ID: "+moduleID+". Please Try Again");
				}
			}
			else
			{
				out.write("Module with ID: "+ moduleID +" not found");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
