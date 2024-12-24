package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Option;

public class OptionDAO {

	private String user = "your_db_username";
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
    
    private Option getNextOption(ResultSet rslt)
    {
    	Option option =null;
    	try
    	{
    		option = new Option (
    				rslt.getInt("optionID"),
    				rslt.getString("optionText"),
    				rslt.getBoolean("isCorrect"),
    				rslt.getInt("questionID")
    				);
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return option;
    }
    
    public ArrayList<Option> getAllOptions()
    {
    	ArrayList<Option> allOptions = new ArrayList<>();
    	String selectSQL= "select * from Option";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL);
    		ResultSet rslt= stmnt.executeQuery())
    	{
    		while (rslt.next())
    		{
    			Option option = getNextOption(rslt);
    			allOptions.add(option);
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allOptions;
    }
    
    public Option getOptionByID(int optionID)
    {
    	Option option = null;
    	String selectSQL= "select * from Option where optionID=?";
    	
    	try (Connection dbConn = openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, optionID);
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				option=getNextOption(rslt);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return option;
    }
    
    public ArrayList<Option> getOptionsByQuestionID(int questionID)
    {
    	ArrayList<Option> allOptions = new ArrayList<>();
    	String selectSQL= "select * from Option where questionID=?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt= dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, questionID);
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				Option option = getNextOption(rslt);
    				allOptions.add(option);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allOptions;
    }
    
    public boolean insertOption(Option option)
    {
    	boolean inserted=false;
    	String insertSQL= "insert into Option (optionText, isCorrect, questionID) values(?,?,?)";
    	
    	try(Connection dbConn= openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(insertSQL))
    	{
    		stmnt.setString(1, option.getOptionText());
    		stmnt.setBoolean(2, option.getIsCorrect());
    		stmnt.setInt(3, option.getQuestionID());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			inserted=true;
    			System.out.println("Yes! Option inserted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to insert option");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return inserted;
    }
    
    public boolean deleteOption(int optionID)
    {
    	boolean deleted=false;
    	String deleteSQL= "delete from Option where optionID=?";
    	
    	try(Connection dbConn= openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(deleteSQL))
    	{
    		stmnt.setInt(1, optionID);
    		int rows=stmnt.executeUpdate();
    		
    		if(rows>0)
    		{
    			deleted=true;
    			System.out.println("Yes! Option deleted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to delete option");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return deleted;
    }
    
    public boolean updateOption(Option option)
    {
    	boolean updated=false;
    	String updateSQL= "update Option set optionText=?, isCorrect=? where optionID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(updateSQL))
    	{
    		stmnt.setString(1, option.getOptionText());
    		stmnt.setBoolean(2, option.getIsCorrect());
    		stmnt.setInt(3, option.getOptionID());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			updated=true;
    			System.out.println("Yes! Option updated successfully");
    		}
    		else
    		{
    			System.out.println("Failed to update option");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return updated;
    }
    
}
