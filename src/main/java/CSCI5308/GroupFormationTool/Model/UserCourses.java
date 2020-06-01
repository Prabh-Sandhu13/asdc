package CSCI5308.GroupFormationTool.Model;

import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;

public class UserCourses implements IUserCourses {

	private String courseId;

	private String bannerId;

	private String courseName;

	private String courseDescription;

	private String userRole;

	@Override
	public String getCourseId() {
		return courseId;
	}

	@Override
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String getBannerId() {
		return bannerId;
	}

	@Override
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	@Override
	public String getCourseName() {
		return courseName;
	}

	@Override
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String getCourseDescription() {
		return courseDescription;
	}

	@Override
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	@Override
	public String getUserRole() {
		return userRole;
	}

	@Override
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public UserCourses() {
		courseId = null;
		courseName = null;
		courseDescription = null;
		bannerId = null;
		userRole = null;
	}

}
