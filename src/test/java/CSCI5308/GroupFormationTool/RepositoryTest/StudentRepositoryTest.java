package CSCI5308.GroupFormationTool.RepositoryTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.Repository.StudentRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRepositoryTest {

	private StudentRepository studentRepository;

	@Test
	public void createStudentTest() {

		studentRepository = mock(StudentRepository.class);

		List<StudentCSV> student = new ArrayList<StudentCSV>();

		when(studentRepository.createStudent(student, "2")).thenReturn(null);

		assertEquals(null, studentRepository.createStudent(student, "2"));
	}

}
