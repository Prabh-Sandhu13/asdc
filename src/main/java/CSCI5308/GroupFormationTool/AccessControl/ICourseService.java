package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface ICourseService {

    ArrayList<ICourse> getAllCourses();

    ICourse getCourseById(String courseId);

    boolean deleteCourse(String courseId);

    boolean createCourse(ICourse course);

}
