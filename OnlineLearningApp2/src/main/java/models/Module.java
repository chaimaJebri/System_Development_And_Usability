package models;

public class Module {

	private int moduleID;   //Primary key Auto-increment in the Database
	private String moduleTitle;
	private String moduleOverview;
	private String moduleContent;
	private int courseID;       //Foreign Key
	
	public Module()
	{
		super();
	}
	
	public Module (int moduleID, String moduleTitle, String moduleOverview, String moduleContent, int courseID)
	{
		this.moduleID=moduleID;
		this.moduleTitle=moduleTitle;
		this.moduleOverview=moduleOverview;
		this.moduleContent=moduleContent;
		this.courseID=courseID;
	}
	
	public Module (String moduleTitle, String moduleOverview, String moduleContent, int courseID)
	{
		this.moduleTitle=moduleTitle;
		this.moduleOverview=moduleOverview;
		this.moduleContent=moduleContent;
		this.courseID=courseID;
	}
	
	public int getModuleID()
	{
		return this.moduleID;
	}
	
	public void setModuleID(int moduleID)
	{
		this.moduleID=moduleID;
	}
	
	public String getModuleTitle()
	{
		return this.moduleTitle;
	}
	
	public void setModuleTitle(String moduleTitle)
	{
		this.moduleTitle=moduleTitle;
	}
	
	public String getModuleOverview()
	{
		return this.moduleOverview;
	}
	
	public void setModuleOverview(String moduleOverview)
	{
		this.moduleOverview=moduleOverview;
	}
	public String getModuleContent()
	{
		return this.moduleContent;
	}
	public void setModuleContent(String moduleContent)
	{
		this.moduleContent=moduleContent;
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
		return "Module [ id= "+this.moduleID+", Title= "+this.moduleTitle+", Overview= "+this.moduleOverview+", Content= "+this.moduleContent+", Course related id= "+this.courseID+" ]";
	}
}
