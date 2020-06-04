package CSCI5308.GroupFormationTool.DBMock;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Model.UserCourses;

public class UserCoursesDBMock implements IUserCoursesRepository {

	private String courseId;

	private String bannerId;

	private String courseName;

	private String courseDescription;

	private String userRole;

	private String id;

	private String name;

	private int credits;

	private String description;

	public UserCoursesDBMock() {
		courseId = "CSCI5308";
		courseName = "Adv SDC";
		courseDescription = "sample";
		bannerId = "B00854462";
		userRole = "student";

		id = "CSCI5308";
		name = "Adv SDC";
		credits = 3;
		description = "sample";
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

	@Override
	public String getUserRoleByEmailId(String emailId) {
		if (emailId.equals("student@dal.ca")) {
			return "Student";
		} else if (emailId.equals("ta@dal.ca")) {
			return "TA";
		} else if (emailId.equals("instructor@dal.ca")) {
			return "Instructor";
		}
		return "Guest";

	}

	@Override
	public ArrayList<ICourse> getStudentCourses(String emailId) {
		ArrayList<ICourse> studentCourseList = new ArrayList<>();
		ICourse course = new Course();

		course.setCredits(credits);
		course.setDescription(description);
		course.setName(name);
		course.setId(id);

		studentCourseList.add(course);

		return studentCourseList;
	}

	@Override
	public ArrayList<ICourse> getTACourses(String emailId) {
		ArrayList<ICourse> taCourseList = new ArrayList<>();
		ICourse course = new Course();

		course.setCredits(credits);
		course.setDescription(description);
		course.setName(name);
		course.setId(id);

		taCourseList.add(course);

		return taCourseList;
	}

	public ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addInstructorsToCourse(Long instructor, String courseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ICourse> getInstructorCourses(String emailId) {
		ArrayList<ICourse> instCourseList = new ArrayList<>();
		ICourse course = new Course();

		course.setCredits(credits);
		course.setDescription(description);
		course.setName(name);
		course.setId(id);

		instCourseList.add(course);

		return instCourseList;
	}

	@Override
	public ArrayList<IUser> getTAForCourse(String courseId) {
		ArrayList<IUser> taList = new ArrayList<>();
		IUser ta = new User();
		ta.setBannerId(bannerId);
		ta.setEmailId("stu@gmail.com");
		ta.setFirstName("John");
		ta.setLastName("sam");
		taList.add(ta);
		return taList;
	}

	@Override
	public boolean enrollTAForCourseUsingEmailId(User user, String courseId) {
		if(courseId=="1") {
			return true;
		}
		return false;
	}

}
