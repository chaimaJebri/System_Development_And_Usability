package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Question;

public class QuestionDAO {

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
    
    private Question getNextQuestion(ResultSet rslt)
    {
    	Question question = null;
    	try
    	{
    		question = new Question (
    				rslt.getInt("questionID"),
    				rslt.getString("questionText"),
    				rslt.getInt("quizID")
    				);
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return question;
    }
	
    public ArrayList<Question> getAllQuestions()
    {
    	ArrayList<Question> allQuestions = new ArrayList<>();
    	String selectSQL = "select * from Question";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL);
    		ResultSet rslt = stmnt.executeQuery())
    	{
    		while (rslt.next())
    		{
    			Question question = getNextQuestion(rslt);
    			allQuestions.add(question);
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allQuestions;
    }
    
    public Question getQuestionByID(int questionID)
    {
    	Question question =null;
    	String selectSQL= "select * from Question where questionID=?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, questionID);
    		try (ResultSet rslt = stmnt.executeQuery())
    		{
    			while (rslt.next())
    			{
    				question=getNextQuestion(rslt);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return question;
    }
    
    public ArrayList<Question> getQuestionsByQuizID(int quizID)
    {
    	ArrayList<Question> allQuestions = new ArrayList<>();
    	String selectSQL= "select * from Question where quizID=?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, quizID);
    		try(ResultSet rslt=stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				Question question=getNextQuestion(rslt);
    				allQuestions.add(question);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return allQuestions;
    }
    
    public int insertQuestion(Question question)
    {
    	int questionID=-1;
    	String insertSQL= "insert into Question (questionText, quizID) values (?,?)";
    	
    	try (Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS))
    	{
    		stmnt.setString(1, question.getQuestionText());
    		stmnt.setInt(2, question.getQuizID());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			try(ResultSet rslt = stmnt.getGeneratedKeys())
    			{
    				if (rslt.next())
    				{
    					questionID=rslt.getInt(1);
    				}
    					
    			}
    			System.out.println("Yes! Question inserted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to insert Question");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return questionID;
    }
    
    public boolean deleteQuestion(int questionID)
    {
    	boolean deleted=false;
    	String deleteSQL= "delete from Question where questionID=?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(deleteSQL))
    	{
    		stmnt.setInt(1, questionID);
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			deleted=true;
    			System.out.println("Yes! Question deleted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to delete question");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return deleted;
    }
    
    public boolean updateQuestion(Question question)
    {
    	boolean updated=false;
    	String updateSQL= "update Question set questionText=? where questionID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(updateSQL))
    	{
    		stmnt.setString(1, question.getQuestionText());
    		stmnt.setInt(2, question.getQuestionID());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			updated=true;
    			System.out.println("Yes! Question updated successfully");
    		}
    		else
    		{
    			System.out.println("Failed to update Question");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return updated;
    }
}
