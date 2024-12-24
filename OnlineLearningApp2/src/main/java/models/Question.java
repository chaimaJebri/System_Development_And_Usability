package models;

public class Question {
	
	private int questionID;          //Primary Key Auto-increment in the Database
	private String questionText;
	private int quizID;             //Foreign Key
	
	public Question()
	{
		super();
	}
	
	public Question(int questionID, String questionText, int quizID)
	{
		this.questionID=questionID;
		this.questionText=questionText;
		this.quizID=quizID;
	}
	
	public Question(String questionText, int quizID)
	{
		this.questionText=questionText;
		this.quizID=quizID;
	}
	
	public int getQuestionID()
	{
		return this.questionID;
	}
	public void setQuestionID(int questionID)
	{
		this.questionID=questionID;
	}
	
	public String getQuestionText()
	{
		return this.questionText;
	}
	public void setQuestionText(String questionText)
	{
		this.questionText=questionText;
	}
	
	public int getQuizID()
	{
		return this.quizID;
	}
	public void setQuizID(int quizID)
	{
		this.quizID=quizID;
	}
	
	public String toString()
	{
		return "Question [ id= "+this.questionID+", Text= "+this.questionText+", Quiz related id= "+this.quizID+" ]";
	}
	//Author: chaimaJebri
}
