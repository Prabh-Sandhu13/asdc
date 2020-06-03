package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface ICourseRepository {

	public ArrayList<ICourse> getAllCourses();
	public boolean createCourse(ICourse course);
	public boolean deleteCourse(String id);	
	public ICourse getCourseById(String courseId);

}
