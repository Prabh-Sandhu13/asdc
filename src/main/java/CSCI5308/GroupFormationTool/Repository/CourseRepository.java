package CSCI5308.GroupFormationTool.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.Course;

public class CourseRepository implements ICourseRepository {

	@Override
	public ArrayList<ICourse> getAllCourses() {

		StoredProcedure storedProcedure = null;
		ArrayList<ICourse> courseList = new ArrayList<ICourse>();
		try {
			storedProcedure = new StoredProcedure("sp_getAllCourseDetails");
			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						ICourse course = new Course();
						course.setId(results.getString("course_id"));
						course.setName(results.getString("course_name"));
						course.setDescription(results.getString("course_details"));
						course.setCredits(results.getInt("course_credits"));

						courseList.add(course);
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
