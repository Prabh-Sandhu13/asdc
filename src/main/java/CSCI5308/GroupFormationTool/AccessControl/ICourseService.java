package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Model.Course;

public interface ICourseService {

	public ArrayList<ICourse> getAllCourses();
	
	public ICourse getCourseById(String courseId);
	
	public boolean sendBatchMail(ArrayList<IStudentCSV> users, String courseID, String courseName);
	
}
