package CSCI5308.GroupFormationTool.DBMock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.IStudentCSV;
import CSCI5308.GroupFormationTool.AccessControl.IStudentRepository;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

public class StudentDBMock implements IStudentRepository{

	private String firstName;

	private String lastName;

	private String email;

	private String bannerId;
	
	private String password;

	public StudentDBMock() {

		firstName = "Tanu";
		lastName = "Gulia";
		email = "tn300318@dal.ca";
		bannerId = "B00839890";
		password = "password1234";

	}

	public ArrayList<IStudentCSV> getAllStudents() {

		ArrayList<IStudentCSV> studentList = new ArrayList<>();
		IStudentCSV student = new StudentCSV();

		student.getFirstName();
		student.getLastName();
		student.getEmail();
		student.getBannerId();
		student.getPassword();

		studentList.add(student);

		return studentList;
	}

	public IStudentCSV loadStudents(IStudentCSV student) {
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setBannerId(bannerId);
		student.setPassword(password);
		
		return student;
	}

	@Override
	public Map<Integer, List<StudentCSV>> createStudent(List<StudentCSV> student, String courseId) {
		// TODO Auto-generated method stub
		return null;
	}	
	


}
