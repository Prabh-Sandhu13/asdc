package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.User;

import java.util.ArrayList;

public interface IUserCoursesService {

    ArrayList<IUserCourses> getRoleBasedCourses(String emailId);

    ArrayList<ICourse> getStudentCourses(String emailId);

    String getUserRoleByEmailId(String emailId);

    ArrayList<ICourse> getTACourses(String emailId);

    ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId);

    boolean addInstructorsToCourse(Long instructor, String courseId);

    ArrayList<ICourse> getInstructorCourses(String emailId);

    ArrayList<IUser> getInstructorsForCourse(String courseId);

    ArrayList<IUser> getTAForCourse(String courseId);

    boolean enrollTAForCourseUsingEmailId(User user, String courseId);

}
