package CSCI5308.GroupFormationTool.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.UserCourses;

public class UserCoursesRepository implements IUserCoursesRepository {

	@Override
	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId) {
		StoredProcedure storedProcedure = null;
		ArrayList<IUserCourses> courseList = new ArrayList<IUserCourses>();
		try {
			storedProcedure = new StoredProcedure("sp_getRoleBasedCourses(?)");
			storedProcedure.setInputStringParameter(1, emailId);
			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						IUserCourses userCourse = new UserCourses();
						userCourse.setBannerId(results.getString("banner_id"));
						userCourse.setCourseId(results.getString("course_id"));
						userCourse.setCourseName(results.getString("course_name"));
						userCourse.setUserRole(results.getString("user_role"));
						userCourse.setCourseDescription(results.getString("course_details"));

						courseList.add(userCourse);
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return courseList;
	}
}
