package CSCI5308.GroupFormationTool.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IStudentCSV;
import CSCI5308.GroupFormationTool.DBMock.StudentDBMock;

@SpringBootTest
public class StudentCSVTest {

	private IStudentCSV createDefaultStudentCSV() {
		StudentDBMock studentDBMock = new StudentDBMock();
		IStudentCSV studentCSV = loadStudentCSV(studentDBMock);
		return studentCSV;
	}

	private IStudentCSV loadStudentCSV(StudentDBMock studentDBMock) {

		IStudentCSV studentCSV = new StudentCSV();
		studentCSV = studentDBMock.loadStudentCSVWithID(studentCSV);
		return studentCSV;
	}

	@Test
	public void getFirstNameTest() {
		IStudentCSV studentCSV = createDefaultStudentCSV();
		assertEquals("Tanu", studentCSV.getFirstName());
	}

	@Test
	public void setFirstNameTest() {
		IStudentCSV studentCSV = new StudentCSV();
		studentCSV.setFirstName("Padmesh");
		assertEquals("Padmesh",studentCSV.getFirstName());
	
	}

	@Test
	public void getLastNameTest() {
		IStudentCSV studentCSV = createDefaultStudentCSV();
		assertEquals("Gulia", studentCSV.getLastName());
	}

	@Test
	public void setLastNameTest() {
		IStudentCSV studentCSV = new StudentCSV();
		studentCSV.setLastName("Donthu");
		assertEquals("Donthu",studentCSV.getLastName());
	
	}

	@Test
	public void getBannerIdTest() {
		IStudentCSV studentCSV = createDefaultStudentCSV();
		assertEquals("B00839890", studentCSV.getBannerId());
	}

	@Test
	public void setBannerIdTest() {
		IStudentCSV studentCSV = new StudentCSV();
		studentCSV.setBannerId("B0000000");
		assertEquals("B0000000",studentCSV.getBannerId());
	
	}

	@Test
	public void getEmailIdTest() {
		IStudentCSV studentCSV = createDefaultStudentCSV();
		assertEquals("tn300318@dal.ca", studentCSV.getEmail());
	}

	@Test
	public void setEmailIdTest() {
		IStudentCSV studentCSV = new StudentCSV();
		studentCSV.setEmail("padmeshdonthu@gmail.com");
		assertEquals("padmeshdonthu@gmail.com",studentCSV.getEmail());
	}

	@Test
	public void getPasswordTest() {
		IStudentCSV studentCSV = createDefaultStudentCSV();
		assertEquals("password1234", studentCSV.getPassword());
	}

	@Test
	public void setPasswordTest() {
		IStudentCSV studentCSV = new StudentCSV();
		studentCSV.setPassword("password");
		assertEquals("password",studentCSV.getPassword());
	}

}