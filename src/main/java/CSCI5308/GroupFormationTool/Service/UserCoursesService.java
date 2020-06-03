package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesService;
import CSCI5308.GroupFormationTool.Model.User;

public class UserCoursesService implements IUserCoursesService {

	IUserCoursesRepository userCoursesRepository;

	@Override
	public ArrayList<IUserCourses> getRoleBasedCourses(String emailId) {

		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.getRoleBasedCourses(emailId);

	}

	@Override
	public String getUserRoleByEmailId(String emailId) {

		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.getUserRoleByEmailId(emailId);
	}

	@Override
	public ArrayList<ICourse> getStudentCourses(String emailId) {

		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.getStudentCourses(emailId);

	}

	@Override
	public ArrayList<ICourse> getTACourses(String emailId) {

		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.getTACourses(emailId);

	}

	@Override
	public ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId) {

		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.usersCurrentlyNotInstructorsForCourse(courseId);

	}

	@Override
	public boolean addInstructorsToCourse(Long instructor, String courseId) {
		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.addInstructorsToCourse(instructor, courseId);
	}

	@Override
	public ArrayList<ICourse> getInstructorCourses(String emailId) {
		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.getInstructorCourses(emailId);
	}

	@Override
	public ArrayList<IUser> getTAForCourse(String courseId) {
		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.getTAForCourse(courseId);
	}

	@Override
	public boolean enrollTAForCourseUsingEmailId(User user, String courseId) {
		userCoursesRepository = Injector.instance().getUserCoursesRepository();

		return userCoursesRepository.enrollTAForCourseUsingEmailId(user,courseId);
	}

}
