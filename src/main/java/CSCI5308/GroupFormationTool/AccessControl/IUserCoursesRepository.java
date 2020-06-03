package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IUserCoursesRepository {

	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId);

	public String getUserRoleByEmailId(String emailId);

	public ArrayList<ICourse> getStudentCourses(String emailId);

	public ArrayList<ICourse> getTACourses(String emailId);

	public ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId);

	public boolean addInstructorsToCourse(Long instructor, String courseId);

	public ArrayList<ICourse> getInstructorCourses(String emailId);

}
