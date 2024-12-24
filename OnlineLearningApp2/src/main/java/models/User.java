package models;

public class User {

	private int userID;           //primary key and auto-increment in the database
	private String fullName;
	private String username;
	private String emailAddress;
	private String password;
	private String interests;
	private String role;
	
	public User ()
	{
		super();
	}
	
	public User (int userID, String fullName, String username, String emailAddress, String password, String interests,String role)
	{
		this.userID=userID;
		this.fullName=fullName;
		this.username=username;
		this.emailAddress=emailAddress;
		this.password=password;
		this.interests=interests;
		this.role=role;
	}
	
	public User (String fullName, String username, String emailAddress, String password, String interests,String role)
	{
		this.fullName=fullName;
		this.username=username;
		this.emailAddress=emailAddress;
		this.password=password;
		this.interests=interests;
		this.role=role;
	}
	
	public int getUserID()
	{
		return this.userID;
	}
	public void setUserID(int userID)
	{
		this.userID=userID;
	}
	
	public String getFullName()
	{
		return this.fullName;
	}
	public void setFullName(String fullName)
	{
		this.fullName=fullName;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	public String getEmailAddress()
	{
		return this.emailAddress;
	}
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress=emailAddress;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getInterests()
	{
		return this.interests;
	}
	public void setInterests(String interests)
	{
		this.interests=interests;
	}
	
	public String getRole()
	{
		return this.role;
	}
	public void setRole(String role)
	{
		this.role=role;
	}
	//Author: chaimaJebri
}
