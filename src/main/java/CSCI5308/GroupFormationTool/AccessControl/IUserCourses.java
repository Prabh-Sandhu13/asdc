package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserCourses {
	public String getCourseId();

	public void setCourseId(String courseId);

	public String getBannerId();

	public void setBannerId(String bannerId);

	public String getCourseName();

	public void setCourseName(String courseName);

	public String getCourseDescription();

	public void setCourseDescription(String courseDescription);

	public String getUserRole();

	public void setUserRole(String userRole);
}
