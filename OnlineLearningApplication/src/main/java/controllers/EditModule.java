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

@WebServlet("/EditModule")
public class EditModule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModuleDAO moduleDao;
       
    public EditModule() {
        super();
        moduleDao= new ModuleDAO();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int courseID= Integer.parseInt(request.getParameter("relatedCourseID-edit"));	
		int moduleID= Integer.parseInt(request.getParameter("moduleID-edit"));
		String moduleTitle=request.getParameter("editedModuleTitle");
		String moduleOverview = request.getParameter("editedModuleOverview");
		String moduleContent = request.getParameter("editedModuleContent");
		response.setContentType("text/plain");
		
		try(PrintWriter out = response.getWriter())
		{
			if(moduleDao.getModuleByID(moduleID) != null)
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
				
				Module module = new Module(moduleID, moduleTitle, moduleOverview, moduleContent, courseID);
				boolean edited = moduleDao.updateModule(module);
				if (edited)
				{
					out.print("Module with ID: "+moduleID+", edited successfully");
				}
				else
				{
					out.print("Oups! Failed to edit module with ID: "+moduleID+ ". Please Try Again");
				}
			}
			else
			{
				out.print("Module with ID: "+moduleID+", not found");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
