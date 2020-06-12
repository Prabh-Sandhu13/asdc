package CSCI5308.GroupFormationTool.DBMock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.IStudentCSV;
import CSCI5308.GroupFormationTool.AccessControl.IStudentRepository;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

public class StudentDBMock implements IStudentRepository {

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

		StudentCSV newUsers = new StudentCSV();
		newUsers.setFirstName(firstName);
		newUsers.setLastName(lastName);
		newUsers.setEmail(email);
		newUsers.setBannerId(bannerId);
		newUsers.setPassword(password);

		List<StudentCSV> newUsersList = new ArrayList<>();

		newUsersList.add(newUsers);

		StudentCSV oldUsers = new StudentCSV();
		oldUsers.setFirstName("Padmesh");
		oldUsers.setLastName("Donthu");
		oldUsers.setEmail("padmeshdonth@gmail.com");
		oldUsers.setBannerId("B00854462");
		oldUsers.setPassword("testsample");

		List<StudentCSV> oldUsersList = new ArrayList<>();

		oldUsersList.add(oldUsers);

		Map<Integer, List<StudentCSV>> map = new HashMap<>();

		map.put(0, newUsersList);
		map.put(1, oldUsersList);

		return map;
	}

	public IStudentCSV loadStudentCSVWithID(IStudentCSV studentCSV) {
		studentCSV.setBannerId(bannerId);
		studentCSV.setEmail(email);
		studentCSV.setFirstName(firstName);
		studentCSV.setLastName(lastName);
		studentCSV.setPassword(password);
		return studentCSV;
	}

}
