package models;

public class Quiz {
	
	private int quizID;          //Primary Key Auto-increment in the Database
	private String quizName;
	private String quizOverview;
	private int courseID;         //Foreign Key
	
	public Quiz()
	{
		super();
	}

	public Quiz (int quizID, String quizName, String quizOverview, int courseID)
	{
		this.quizID=quizID;
		this.quizName=quizName;
		this.quizOverview=quizOverview;
		this.courseID=courseID;
	}
	
	public Quiz (String quizName, String quizOverview, int courseID)
	{
		this.quizName=quizName;
		this.quizOverview=quizOverview;
		this.courseID=courseID;
	}
	
	public int getQuizID()
	{
		return this.quizID;
	}
	public void setQuizID(int quizID)
	{
		this.quizID=quizID;
	}
	
	public String getQuizName()
	{
		return this.quizName;
	}
	public void setQuizName(String quizName)
	{
		this.quizName=quizName;
	}
	
	public String getQuizOverview()
	{
		return this.quizOverview;
	}
	public void setQuizOverview(String quizOverview)
	{
		this.quizOverview=quizOverview;
	}
	
	public int getCourseID()
	{
		return this.courseID;
	}
	public void setCourseID(int courseID)
	{
		this.courseID=courseID;
	}
	
	public String toString()
	{
		return "Quiz [ id= "+this.quizID+", Name= "+this.quizName+", Overview= "+this.quizOverview+", Course related id= "+this.courseID+" ]";
	}
	//Author: chaimaJebri
}
