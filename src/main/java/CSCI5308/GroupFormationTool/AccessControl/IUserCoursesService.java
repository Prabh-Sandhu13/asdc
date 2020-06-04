package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Model.User;

public interface IUserCoursesService {
	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId);

	public ArrayList<ICourse> getStudentCourses(String emailId);

	public String getUserRoleByEmailId(String emailId);

	public ArrayList<ICourse> getTACourses(String emailId);

	public ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId);

	public boolean addInstructorsToCourse(Long instructor, String courseId);

	public ArrayList<ICourse> getInstructorCourses(String emailId);
	
	public ArrayList<IUser> getInstructorsForCourse(String courseId);


	public ArrayList<IUser> getTAForCourse(String courseId);

	public boolean enrollTAForCourseUsingEmailId(User user, String courseId);

}
