package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Module;

public class ModuleDAO {

	private String user = "you_db_username";
    private String password = "your_db_password";
    private String url = "jdbc:mysql://MySQL_server_address:6306/"+user;
    
    private Connection openConnection()
 	{
 		Connection dbConn=null;
 			try
 			{
 			    Class.forName("com.mysql.jdbc.Driver");
 			} 
 			catch(Exception e) 
 			{ 
 				System.out.println(e); 
 		    }
 			try
 			{
 	 			dbConn = DriverManager.getConnection(url, user, password);
 			} 
 			catch(SQLException se) 
 			{ 
 				System.out.println(se); 
 			}	  
 	 
 		return dbConn;
     }

    private Module getNextModule(ResultSet rslt)
    {
    	Module module =null;
    	try
    	{
    		module = new Module (
    						rslt.getInt("moduleID"),
    						rslt.getString("moduleTitle"),
    						rslt.getString("moduleOverview"),
    						rslt.getString("moduleContent"),
    						rslt.getInt("courseID")
    				);
    	}
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return module;
    }
	
    public ArrayList<Module> getAllModules()
    {
    	ArrayList<Module> allModules = new ArrayList<>();
    	String selectSQL="select * from Module";
    	
    	try (Connection dbConn = openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(selectSQL);
    		 ResultSet rslt=stmnt.executeQuery())
    	{
    		while (rslt.next())
    		{
    			Module module = getNextModule(rslt);
    			allModules.add(module);
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allModules;
    }

    public Module getModuleByID (int moduleID)
    {
    	Module module=null;
    	String selectSQL="select * from Module where moduleID=?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, moduleID);
    		try(ResultSet rslt= stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				module=getNextModule(rslt);
    			}
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return module;
    }
    
    public ArrayList<Module> getModulesByCourseID(int courseID)
    {
    	ArrayList<Module> allModules = new ArrayList<>();
    	String selectSQL ="select * from Module where courseID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, courseID);
    		try(ResultSet rslt=stmnt.executeQuery())
    		{
    			while (rslt.next())
    			{
    				Module module=getNextModule(rslt);
    				allModules.add(module);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allModules;
    }
    
    public boolean insertModule(Module module)
    {
    	boolean inserted = false;
    	String insertSQL ="insert into Module (moduleTitle, moduleOverview,moduleContent,courseID) values (?,?,?,?)";
    	
    	try (Connection dbConn = openConnection();
    		 PreparedStatement stmnt =dbConn.prepareStatement(insertSQL))
    	{
    		stmnt.setString(1, module.getModuleTitle());
    		stmnt.setString(2, module.getModuleOverview());
    		stmnt.setString(3, module.getModuleContent());
    		stmnt.setInt(4, module.getCourseID());
    		
    		int rows=stmnt.executeUpdate();
    		if (rows>0)
    		{
    			inserted=true;
    			System.out.println("Yes! Module inserted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to insert Module");
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return inserted;
    }
    
    public boolean deleteModule (int moduleID)
    {
    	boolean deleted =false;
    	String deleteSQL ="delete from Module where moduleID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(deleteSQL))
    	{
    		stmnt.setInt(1, moduleID);
    		int rows=stmnt.executeUpdate();
    		if (rows>0)
    		{
    			deleted=true;
    			System.out.println("Yes! Module deleted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to delete Module");
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return deleted;
    }
    
    public boolean updateModule (Module module)
    {
    	boolean updated=false;
    	String updateSQL ="update Module set moduleTitle=?, moduleOverview=?, moduleContent=? where moduleID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(updateSQL))
    	{
    		stmnt.setString(1, module.getModuleTitle());
    		stmnt.setString(2, module.getModuleOverview());
    		stmnt.setString(3, module.getModuleContent());
    		stmnt.setInt(4, module.getModuleID());
    		
    		int rows=stmnt.executeUpdate();
    		if (rows>0)
    		{
    			updated=true;
    			System.out.println("Yes! Module updated successfully");
    		}
    		else
    		{
    			System.out.println("Failed to update Module");
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return updated;
    }

    public ArrayList<Module> searchModule(int courseID, String searchStr)
    {
    	ArrayList<Module> searchedModules = new ArrayList<>();
    	String searchSQL="select * from Module where courseID = ? AND  moduleTitle like ?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(searchSQL))
    	{
    		stmnt.setInt(1, courseID);
    		stmnt.setString(2,"%"+searchStr+"%");
    		try(ResultSet rslt=stmnt.executeQuery())
    		{
    			while (rslt.next())
    			{
    				Module module=getNextModule(rslt);
    				searchedModules.add(module);
    			}
    		}	
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return searchedModules;
    }
}
