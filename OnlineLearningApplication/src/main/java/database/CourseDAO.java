package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Course;

public class CourseDAO {
	
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

    
    private Course getNextCourse(ResultSet rslt)
    {
    	Course course = null;
    	try
    	{
    		course = new Course (
    				rslt.getInt("courseID"),
    				rslt.getString("courseName"),
    				rslt.getString("description")
    				);
    	}
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return course;
    }

    public ArrayList<Course> getAllCourses()
    {
    	ArrayList <Course> allCourses = new ArrayList<Course>();
    	String selectSQL = "select * from Course";
    	
    	try (Connection dbConn =openConnection();
   			 PreparedStatement stmnt=dbConn.prepareStatement(selectSQL);
   			 ResultSet rslt = stmnt.executeQuery())
   		{
   		    
   		    while(rslt.next())
   		    {
   		    	Course course = getNextCourse(rslt);
   		    	allCourses.add(course);
   		    }
   		} 
   		catch(SQLException e) 
   		{ 
   			System.out.println(e.getMessage()); 
   		}
    	return allCourses;
    }
    
    public Course getCourseByID (int id)
    {
    	Course course = null;
    	String selectSQL = "select * from Course where courseID= ?";
    	
    	try (Connection dbConn = openConnection();
			PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, id);
    		try (ResultSet rslt = stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				course=getNextCourse(rslt);
    			}
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.print(e.getMessage());
    	}
    	return course;
    }	
    
    public boolean deleteCourse (int id)
    {
    	boolean deleted = false;
    	String deleteSQL ="delete from Course where courseID=?";
    	
    	try (Connection dbConn=openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(deleteSQL))
    	{
    		stmnt.setInt(1,  id);
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			deleted=true;
    			System.out.println("Yes ! Course deleted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to delete Course");
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return deleted;
    }
    
    public boolean insertCourse (Course course)
    {
    	boolean inserted = false;
    	String insertSQL ="insert into Course(courseName, description) values(?,?)";
    	
    	try (Connection dbConn = openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(insertSQL))
    	{
    		stmnt.setString(1, course.getCourseName());
    		stmnt.setString(2, course.getDescription());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			inserted=true;
    			System.out.println("Yes ! Course inserted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to insert Course");
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return inserted;
    }
    
    public boolean updateCourse(Course course)
    {
    	boolean updated =false;
    	String updateSQL="update Course set courseName=?, description=? where courseID=?";
    	try (Connection dbConn = openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(updateSQL))
    	{
    		stmnt.setString(1, course.getCourseName());
    		stmnt.setString(2, course.getDescription());
    		stmnt.setInt(3, course.getCourseID());
    		int rows=stmnt.executeUpdate();
    		if (rows>0)
    		{
    			updated=true;
    			System.out.println("Yes ! Course updated successfully");
    		}
    		else
    		{
    			System.out.println("Failed to update Course");
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return updated;
    }
    //Author: chaimaJebri
    
    public ArrayList<Course> searchCourse (String searchStr)
    {
    	ArrayList<Course> searchedCourses = new ArrayList<>();
    	String searchSQL = "select * from Course where courseName like ?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(searchSQL))
    	{
    		stmnt.setString(1, "%"+searchStr+"%");
    		try(ResultSet rslt=stmnt.executeQuery())
    		{
    			while (rslt.next())
    			{
    				Course course=getNextCourse(rslt);
    				searchedCourses.add(course);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return searchedCourses;
    }
}
