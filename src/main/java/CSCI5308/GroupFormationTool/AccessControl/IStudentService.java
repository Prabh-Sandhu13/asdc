package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import CSCI5308.GroupFormationTool.Model.StudentCSV;

public interface IStudentService {
	public Map<Integer,List<StudentCSV>> createStudent(MultipartFile file, String courseId);
}
