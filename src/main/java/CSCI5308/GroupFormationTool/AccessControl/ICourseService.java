package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

public interface ICourseService {

	public ArrayList<ICourse> getAllCourses();
	
	public ICourse getCourseById(String courseId);
	
	public boolean sendBatchMail(List<StudentCSV> list, String courseID);
	
}
