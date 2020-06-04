package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Model.StudentCSV;

public interface IStudentService {
	public Map<Integer,List<StudentCSV>> createStudent(List<StudentCSV> student, String courseId);
}
