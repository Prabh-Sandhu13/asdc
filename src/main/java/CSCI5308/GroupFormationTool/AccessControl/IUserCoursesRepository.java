package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IUserCoursesRepository {
	
	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId);

}
