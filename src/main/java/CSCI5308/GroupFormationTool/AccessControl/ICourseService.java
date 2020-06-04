package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

public interface ICourseService {

	public ArrayList<ICourse> getAllCourses();
	
	public ICourse getCourseById(String courseId);

	boolean deleteCourse(String courseId);

	boolean createCourse(Course course);
	
	public boolean sendBatchMail(List<StudentCSV> list, String courseID);
	
}
