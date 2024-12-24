package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Quiz;

public class QuizDAO {

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

    private Quiz getNextQuiz(ResultSet rslt)
    {
    	Quiz quiz=null;
    	try
    	{
    		quiz= new Quiz(
    				rslt.getInt("quizID"),
    				rslt.getString("quizName"),
    				rslt.getString("quizOverview"),
    				rslt.getInt("courseID")
    				);
    	}
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return quiz;
    }
    
    public ArrayList<Quiz> getAllQuizzes()
    {
    	ArrayList<Quiz> allQuizzes = new ArrayList<>();
    	String selectSQL="select * from Quiz";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt= dbConn.prepareStatement(selectSQL);
    		ResultSet rslt=stmnt.executeQuery())
    	{
    		while(rslt.next())
    		{
    			Quiz quiz=getNextQuiz(rslt);
    			allQuizzes.add(quiz);
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allQuizzes;
    }
	
    public Quiz getQuizByID(int quizID)
    {
    	Quiz quiz=null;
    	String selectSQL="select * from Quiz where quizID=?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, quizID);
    		try(ResultSet rslt=stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				quiz=getNextQuiz(rslt);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return quiz;
    }
  
    public ArrayList<Quiz> getQuizzesByCourseID(int courseID)
    {
    	ArrayList<Quiz> allQuizzes= new ArrayList<>();
    	String selectSQL="select * from Quiz where courseID=?";
    	
    	try(Connection dbConn= openConnection();
    		PreparedStatement stmnt= dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, courseID);
    		try(ResultSet rslt=stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				Quiz quiz = getNextQuiz(rslt);
    				allQuizzes.add(quiz);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allQuizzes;
    }
    
    public boolean insertQuiz(Quiz quiz)
    {
    	boolean inserted=false;
    	String insertSQL="insert into Quiz (quizName, quizOverview, courseID) values(?,?,?)";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(insertSQL))
    	{
    		stmnt.setString(1, quiz.getQuizName());
    		stmnt.setString(2, quiz.getQuizOverview());
    		stmnt.setInt(3, quiz.getCourseID());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			inserted=true;
    			System.out.println("Yes! Quiz inserted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to insert Quiz");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return inserted;
    }
    
    public boolean deleteQuiz(int quizID)
    {
    	boolean deleted=false;
    	String deleteSQL="delete from Quiz where quizID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(deleteSQL))
    	{
    		stmnt.setInt(1, quizID);
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			deleted=true;
    			System.out.println("Yes! Quiz deleted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to delete Quiz");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return deleted;
    }
    
    public boolean updateQuiz(Quiz quiz)
    {
    	boolean updated=false;
    	String updateSQL="update Quiz set quizName=?, quizOverview=? where quizID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(updateSQL))
    	{
    		stmnt.setString(1, quiz.getQuizName());
    		stmnt.setString(2, quiz.getQuizOverview());
    		stmnt.setInt(3, quiz.getQuizID());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			updated=true;
    			System.out.println("Yes! Quiz updated successfully");
    		}
    		else
    		{
    			System.out.println("Failed to update Quiz");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return updated;
    }
    
    public ArrayList<Quiz> searchQuiz (int courseID, String searchStr)
    {
    	ArrayList<Quiz> searchedQuizzes = new ArrayList<>();
    	String searchSQL="select * from Quiz where courseID=? AND quizName like ? ";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(searchSQL))
    	{
    		stmnt.setInt(1, courseID);
    		stmnt.setString(2, "%"+ searchStr+ "%");
    		
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while (rslt.next())
    			{
    				Quiz quiz=getNextQuiz(rslt);
    				searchedQuizzes.add(quiz);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return searchedQuizzes;
    }
}
