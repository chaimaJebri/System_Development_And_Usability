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
				    out.write("Error");
				    return;
				}
				if (moduleOverview ==null || moduleOverview.trim().isEmpty()) 
				{
				    out.write("Error");
				    return;
				}
				if (moduleContent ==null || moduleContent.trim().isEmpty()) 
				{
				    out.write("Error");
				    return;
				}
				
				Module module = new Module(moduleID, moduleTitle, moduleOverview, moduleContent, courseID);
				boolean edited = moduleDao.updateModule(module);
				if (edited)
				{
					out.print("Success");
				}
				else
				{
					out.print("Failure");
				}
			}
			else
			{
				out.print("Error");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
