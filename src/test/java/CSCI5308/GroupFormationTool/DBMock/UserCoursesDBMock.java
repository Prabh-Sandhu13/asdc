package CSCI5308.GroupFormationTool.DBMock;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.Model.UserCourses;

public class UserCoursesDBMock implements IUserCoursesRepository {

	private String courseId;

	private String bannerId;

	private String courseName;

	private String courseDescription;

	private String userRole;

	public UserCoursesDBMock() {
		courseId = "CSCI5308";
		courseName = "Adv SDC";
		courseDescription = "sample";
		bannerId = "B00854462";
		userRole = "student";
	}

	public IUserCourses loadCourses(IUserCourses userCourses) {

		userCourses.setBannerId(bannerId);
		userCourses.setCourseDescription(courseDescription);
		userCourses.setCourseId(courseId);
		userCourses.setCourseName(courseName);
		userCourses.setUserRole(userRole);
		return userCourses;
	}

	@Override
	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId) {

		ArrayList<IUserCourses> courseList = new ArrayList<>();
		IUserCourses courses = new UserCourses();

		courses.setBannerId(bannerId);
		courses.setCourseDescription(courseDescription);
		courses.setCourseName(courseName);
		courses.setUserRole(userRole);
		courses.setCourseId(courseId);

		courseList.add(courses);

		return courseList;

	}

}
