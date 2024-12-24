package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ModuleDAO;
import models.Module;


@WebServlet("/AddModule")
public class AddModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ModuleDAO moduleDao;
	
    public AddModule() {
        super();
        moduleDao=new ModuleDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int courseID = Integer.parseInt(request.getParameter("relatedCourseID"));
		String moduleTitle= request.getParameter("addedModuleTitle");
		String moduleOverview = request.getParameter("addedModuleOverview");
		String moduleContent = request.getParameter("addedModuleContent");
		response.setContentType("text/plain");
		try(PrintWriter out = response.getWriter())
		{
			if (moduleTitle ==null || moduleTitle.trim().isEmpty()) 
			{
			    out.write("Please provide valid title for the module");
			    return;
			}
			if (moduleOverview ==null || moduleOverview.trim().isEmpty()) 
			{
			    out.write("Please provide valid overview for the module");
			    return;
			}
			if (moduleContent ==null || moduleContent.trim().isEmpty()) 
			{
			    out.write("Please provide valid content for the module");
			    return;
			}
			Module module = new Module (moduleTitle, moduleOverview, moduleContent,courseID);
			boolean inserted = moduleDao.insertModule(module);
			if(inserted)
			{
				out.print("Module with title: "+moduleTitle+", added successfully.");
			}
			else
			{
				out.print("Oups! Failed to add module. Please Try Again");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Author: chaimaJebri
}
