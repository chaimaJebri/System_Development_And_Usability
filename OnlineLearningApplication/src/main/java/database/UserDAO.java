package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import models.User;

public class UserDAO {
	
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
    
    
    //This method hashes the password passed in the param using bcrypt
    public String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    //This method verifies if a given password matches the specified hashed password.
    public boolean verifyPassword(String password, String hashedPassword)
    {
    	return BCrypt.checkpw(password, hashedPassword);
    }
    
    public boolean createUserAccount (User user) 
    {
    	boolean created = false;
    	String insertSQL ="insert into User(fullName, username, emailAddress, password, interests, role) values (?,?,?,?,?,?)";
    	
    	try (Connection dbConn = openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(insertSQL))
    	{
    		stmnt.setString(1, user.getFullName());
    		stmnt.setString(2, user.getUsername());
    		stmnt.setString(3, user.getEmailAddress());
    		stmnt.setString(4, hashPassword(user.getPassword()));
    		stmnt.setString(5, user.getInterests());
    		stmnt.setString(6, user.getRole());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			created=true;
    			System.out.println("Yes ! User Account created successfully");
    		}
    		else
    		{
    			System.out.println("Failed to create User Account");
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return created;
    }

    public boolean updateUserAccount(User user)
    {
    	boolean updated =false;
    	String updateSQL="update User set fullName=?, username=?, emailAddress=?, password=?, interests=?, role=? where userID=?";
    	
    	try (Connection dbConn=openConnection();
    		 PreparedStatement stmnt=dbConn.prepareStatement(updateSQL))
    	{
    		stmnt.setString(1,user.getFullName());
    		stmnt.setString(2,user.getUsername());
    		stmnt.setString(3,user.getEmailAddress());
    		stmnt.setString(4,hashPassword(user.getPassword()));
    		stmnt.setString(5,user.getInterests());
    		stmnt.setString(6,user.getRole());
    		stmnt.setInt(7, user.getUserID());
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			updated=true;
    			System.out.println("Yes ! User Account updated successfully");
    		}
    		else
    		{
    			System.out.println("Failed to update User Account");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return updated;
    }

    public boolean deleteUserAccount(int userID)
    {
    	boolean deleted=false;
    	String deleteSQL="delete from User where userID=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt= dbConn.prepareStatement(deleteSQL))
    	{
    		stmnt.setInt(1, userID);
    		
    		int rows=stmnt.executeUpdate();
    		if(rows>0)
    		{
    			deleted=true;
    			System.out.println("Yes ! User Account deleted successfully");
    		}
    		else
    		{
    			System.out.println("Failed to delete User Account");
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return deleted;
    }
    
    public User getUserByUsernameAndPassword(String username, String password)
    {
    	User user =null;
    	String selectSQL="select * from User where username=?";
    	
    	try(Connection dbConn=openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setString(1, username);
    		
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				int userID=rslt.getInt("userID");
    				String fullName=rslt.getString("fullName");
    				String emailAddress=rslt.getString("emailAddress");
    				String hashedPassword= rslt.getString("password");
    				String interests=rslt.getString("interests");
    				String role=rslt.getString("role");
    				
    				if (verifyPassword(password, hashedPassword))
    				{
    					user=new User(userID, fullName, username, emailAddress, hashedPassword, interests, role);
    				}				
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return user;
    }
    
    public boolean isUniqueUsername(String username)
    {
    	boolean isUnique=false;
    	String selectSQL="select COUNT(*)from User where username=?"; //selects how many user has that username
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt=dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setString(1, username);		
    		try(ResultSet rslt =stmnt.executeQuery())
    		{
    			if(rslt.next())
    			{
    				isUnique=rslt.getInt(1)==0;
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return isUnique;
    }
    
    public boolean isUniqueEmailAddress (String emailAddress)
    {
    	boolean isUnique=false;
    	String selectSQL="select COUNT(*) from User where emailAddress=?";
    	
    	try(Connection dbConn=openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setString(1, emailAddress);
    		try(ResultSet rslt=stmnt.executeQuery())
    		{
    			if(rslt.next())
    			{
    				isUnique=rslt.getInt(1)==0;
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return isUnique;
    }
      
    public User getUserByID(int userID)
    {
    	User user =null;
    	String selectSQL="select * from User where userID=?";
    	
    	try(Connection dbConn=openConnection();
    		 PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setInt(1, userID);
    		
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				String fullName=rslt.getString("fullName");
    				String username = rslt.getString("username");
    				String emailAddress=rslt.getString("emailAddress");
    				String password= rslt.getString("password"); //the password in the database is hashed, so he will get the hashed password
    				String interests=rslt.getString("interests");
    				String role=rslt.getString("role");
    				user=new User(userID, fullName, username, emailAddress, password, interests, role);		//return the hashed password, it's not a plain text password	
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return user;
    }
    
    public ArrayList<User> getLearnerAccounts ()
    {
    	ArrayList<User> learnerAccounts = new ArrayList<>();
    	String selectSQL="select * from User where role=?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setString(1, "learner");
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				int userID=rslt.getInt("userID");
    				String fullName= rslt.getString("fullName");
    				String username= rslt.getString("username");
    				String emailAddress= rslt.getString("emailAddress");
    				String hashedPassword= rslt.getString("password");
    				String interests= rslt.getString("interests");
    				String role= rslt.getString("role");
    				
    				User user = new User(userID, fullName, username, emailAddress, hashedPassword, interests, role);
    				learnerAccounts.add(user);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return learnerAccounts;
    }
    
    public ArrayList<User> searchLearnerByUsername(String searchStr)
    {
    	ArrayList<User> searchedLearners = new ArrayList<>();
    	String selectSQL="select * from User where role=? AND username like ?";
    	
    	try(Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))    //a try-with-resources block to close all connections and prevent leaks
    	{
    		stmnt.setString(1, "learner");       //passing the role to the query
    		stmnt.setString(2, "%"+searchStr+"%");  //passing the search string to the query
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while(rslt.next())
    			{
    				int userID=rslt.getInt("userID");
    				String fullName= rslt.getString("fullName");
    				String username= rslt.getString("username");
    				String emailAddress= rslt.getString("emailAddress");
    				String hashedPassword= rslt.getString("password");
    				String interests= rslt.getString("interests");
    				String role= rslt.getString("role");
    				
    				User user = new User(userID, fullName, username, emailAddress, hashedPassword, interests, role);
    				searchedLearners.add(user);
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();  //handling exceptions by printing the stack trace;
    	}
    	return searchedLearners;
    }
    
    public ArrayList<String> getAdminEmailAddresses ()
    {
    	ArrayList<String> emails = new ArrayList<>();
    	String selectSQL="select emailAddress from User where role=?";
    	
    	try (Connection dbConn = openConnection();
    		PreparedStatement stmnt = dbConn.prepareStatement(selectSQL))
    	{
    		stmnt.setString(1, "administrator");
    		
    		try(ResultSet rslt = stmnt.executeQuery())
    		{
    			while (rslt.next())
    			{
    				String emailAddress = rslt.getString("emailAddress");
    				emails.add(emailAddress);
    			}
    		}
    	}
    	catch (SQLException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return emails;
    }
    
    
    
}
