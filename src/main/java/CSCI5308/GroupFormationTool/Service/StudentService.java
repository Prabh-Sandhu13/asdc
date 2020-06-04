package CSCI5308.GroupFormationTool.Service;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IStudentRepository;
import CSCI5308.GroupFormationTool.AccessControl.IStudentService;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

public class StudentService implements IStudentService{

	private IStudentRepository studentRepository;
	
	@Override
	public Map<Integer, List<StudentCSV>> createStudent(List<StudentCSV> student, String courseId) {
		studentRepository = Injector.instance().getStudentRepository();
		return studentRepository.createStudent(student, courseId);
	}

}
