package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IUserCoursesService {
	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId);

}
