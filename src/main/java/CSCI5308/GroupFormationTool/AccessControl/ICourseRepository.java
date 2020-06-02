package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Model.Course;

public interface ICourseRepository {

	public ArrayList<ICourse> getAllCourses();
	public boolean createCourse(ICourse course);
	public boolean deleteCourse(String id);

}
