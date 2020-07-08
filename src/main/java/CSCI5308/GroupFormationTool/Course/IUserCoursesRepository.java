package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.User;

import java.util.ArrayList;

public interface IUserCoursesRepository {

    String getUserRoleByEmailId(String emailId);

    ArrayList<ICourse> getStudentCourses(String emailId);

    ArrayList<ICourse> getTACourses(String emailId);

    ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId);

    boolean addInstructorsToCourse(Long instructor, String courseId);

    ArrayList<ICourse> getInstructorCourses(String emailId);

    ArrayList<IUser> getTAForCourse(String courseId);

    boolean enrollTAForCourseUsingEmailId(User user, String courseId);

    ArrayList<IUser> getInstructorsForCourse(String courseId);

    boolean getUserRoleForCourse(String userId, String courseId);

}
