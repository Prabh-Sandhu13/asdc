package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;


public interface IUserCoursesService {
	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId);
	public ArrayList<ICourse> getStudentCourses(String emailId);
	public String getUserRoleByEmailId(String emailId);
	public ArrayList<ICourse> getTACourses(String emailId);
	

}
