package models;

public class Course {

	private int courseID;  //primary key and auto-increment in the database
	private String courseName;
	private String description;
	
	public Course ()
	{
		super ();
	}
	
	public Course (int courseID, String courseName, String description)
	{
		this.courseID=courseID;
		this.courseName=courseName;
		this.description=description;
	}
	
	public Course (String courseName, String description)
	{
		this.courseName=courseName;
		this.description=description;
	}
	
	public int getCourseID()
	{
		return this.courseID;
	}
	public void setCourseID(int courseID)
	{
		this.courseID=courseID;
	}
	public String getCourseName()
	{
		return this.courseName;
	}
	public void setCourseName(String courseName)
	{
		this.courseName=courseName;
	}
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}
	
	public String toString()
	{
		return "Course [ id= "+this.courseID+", Name= "+this.courseName+", Description= "+this.description + " ]";
	}
}
