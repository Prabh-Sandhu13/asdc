package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;

public class UserCourses implements IUserCourses {

    IUserCoursesRepository userCoursesRepository;

    private String courseId;

    private String bannerId;

    private String courseName;

    private String courseDescription;

    private String userRole;

    public UserCourses() {
        courseId = null;
        courseName = null;
        courseDescription = null;
        bannerId = null;
        userRole = null;
    }

    @Override
    public String getCourseId() {
        return courseId;
    }

    @Override
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String getBannerId() {
        return bannerId;
    }

    @Override
    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String getCourseDescription() {
        return courseDescription;
    }

    @Override
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Override
    public String getUserRole() {
        return userRole;
    }

    @Override
    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
    public boolean enrollTAForCourseUsingEmailId(IUser user, String courseId) {
        userCoursesRepository = Injector.instance().getUserCoursesRepository();
        return userCoursesRepository.enrollTAForCourseUsingEmailId(user, courseId);
    }

    @Override
    public ArrayList<IUser> getInstructorsForCourse(String courseId) {
        userCoursesRepository = Injector.instance().getUserCoursesRepository();
        return userCoursesRepository.getInstructorsForCourse(courseId);
    }

}
