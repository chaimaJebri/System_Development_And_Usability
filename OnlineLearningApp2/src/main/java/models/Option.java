package models;

public class Option {
	
	private int optionID;
	private String optionText;
	private boolean isCorrect;
	private int questionID;
	
	public Option()
	{
		super();
	}
	
	public Option(int optionID, String optionText, boolean isCorrect, int questionID)
	{
		this.optionID=optionID;
		this.optionText=optionText;
		this.isCorrect=isCorrect;
		this.questionID=questionID;
	}
	
	public Option(String optionText, boolean isCorrect, int questionID)
	{
		this.optionText=optionText;
		this.isCorrect=isCorrect;
		this.questionID=questionID;
	}
	
	public int getOptionID()
	{
		return this.optionID;
	}
	public void setOptionID(int optionID)
	{
		this.optionID=optionID;
	}
	
	public String getOptionText()
	{
		return this.optionText;
	}
	public void setOptionText(String optionText)
	{
		this.optionText=optionText;
	}
	
	public boolean getIsCorrect()
	{
		return this.isCorrect;
	}
	public void setIsCorrect(boolean isCorrect)
	{
		this.isCorrect=isCorrect;
	}
	
	public int getQuestionID()
	{
		return this.questionID;
	}
	public void setQuestionID(int questionID)
	{
		this.questionID=questionID;
	}
	
	public String toString()
	{
		return "Option [ id= "+this.optionID+", Text= "+this.optionText+", Is is correct= "+this.isCorrect+", related question id= "+this.questionID+" ]";
	}
	
}
