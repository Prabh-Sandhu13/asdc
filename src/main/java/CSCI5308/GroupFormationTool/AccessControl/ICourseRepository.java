package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface ICourseRepository {

    ArrayList<ICourse> getAllCourses();

    boolean createCourse(ICourse course);

    boolean deleteCourse(String id);

    ICourse getCourseById(String courseId);

}
