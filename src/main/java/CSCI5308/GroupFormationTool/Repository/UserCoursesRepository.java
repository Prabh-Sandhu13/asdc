package CSCI5308.GroupFormationTool.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Model.User;
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

	@Override
	public String getUserRoleByEmailId(String emailId) {
		StoredProcedure storedProcedure = null;
		String role = "Guest";
		try {
			storedProcedure = new StoredProcedure("sp_getUserRoleByEmailId(?)");
			storedProcedure.setInputStringParameter(1, emailId);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				while (results.next()) {
					{
						String roleRet = results.getString("role_type");
						role = roleRet;
						if (roleRet.equals("TA")) {
							break;
						}
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return role;
	}

	@Override
	public ArrayList<ICourse> getStudentCourses(String emailId) {
		StoredProcedure storedProcedure = null;
		ArrayList<ICourse> studentCourseList = new ArrayList<ICourse>();
		try {
			storedProcedure = new StoredProcedure("sp_getStudentCoursesByEmailId(?)");
			storedProcedure.setInputStringParameter(1, emailId);

			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						ICourse course = new Course();
						course.setId(results.getString("course_id"));
						course.setName(results.getString("course_name"));
						course.setDescription(results.getString("course_details"));
						course.setCredits(results.getInt("course_credits"));

						studentCourseList.add(course);
					}
				}
			}
		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return studentCourseList;
	}

	public ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId) {
		StoredProcedure storedProcedure = null;
		ArrayList<IUser> userList = new ArrayList<IUser>();
		try {
			storedProcedure = new StoredProcedure("sp_getUsersCurrentlyNotInstructorsForCourse(?)");
			storedProcedure.setInputStringParameter(1, courseId);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {
				while (results.next()) {
					{
						IUser user = new User();
						user.setBannerId(results.getString("banner_id"));
						user.setEmailId(results.getString("email"));
						user.setFirstName(results.getString("first_name"));
						user.setLastName(results.getString("last_name"));
						user.setId(results.getLong("user_id"));

						userList.add(user);
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return userList;
	}

	@Override
	public ArrayList<ICourse> getTACourses(String emailId) {
		StoredProcedure storedProcedure = null;
		ArrayList<ICourse> taCourseList = new ArrayList<ICourse>();
		try {
			storedProcedure = new StoredProcedure("sp_getTACoursesByEmailId(?)");
			storedProcedure.setInputStringParameter(1, emailId);
			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						ICourse course = new Course();
						course.setId(results.getString("course_id"));
						course.setName(results.getString("course_name"));
						course.setDescription(results.getString("course_details"));
						course.setCredits(results.getInt("course_credits"));

						taCourseList.add(course);
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return taCourseList;
	}

	@Override
	public boolean addInstructorsToCourse(Long instructor, String courseId) {

		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_addInstructorsToCourse(?,?)");

			storedProcedure.setInputIntParameter(1, instructor);
			storedProcedure.setInputStringParameter(2, courseId);

			storedProcedure.execute();

		} catch (SQLException ex) {
			return false;
		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return true;
	}
}