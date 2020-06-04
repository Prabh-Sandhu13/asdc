package CSCI5308.GroupFormationTool.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.DBMock.CourseDBMock;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

@SpringBootTest
public class CourseServiceTest {

	@Test
	public void getAllCoursesTest() {

		CourseService courseService = new CourseService();

		assertNotEquals(null, courseService.getAllCourses());
	}

	@Test
	public void getCourseById() {

		CourseService courseService = new CourseService();

		assertNotEquals(null, courseService.getCourseById("1"));
	}

	@Test
	public void deleteCourse() {

		CourseService courseService = new CourseService();

		assertNotEquals(false, courseService.deleteCourse("00000000000"));
	}

	@Test
	public void createCourse() {
		CourseService courseService = new CourseService();

		CourseDBMock courseDBMock = new CourseDBMock();

		ICourse course = new Course();
		course = courseDBMock.loadCourses(course);

		assertNotEquals(null, courseService.createCourse(course));
	}

	@Test
	public void sendBatchMail() {

		StudentCSV studentCSV = new StudentCSV();
		List<StudentCSV> studentCSVs = new ArrayList<StudentCSV>();

		studentCSV.setEmail("padmeshdonthu@gmail.com");
		studentCSV.setFirstName("Padmesh");
		studentCSV.setLastName("Donthu");
		studentCSV.setPassword("NewPassword");
		studentCSVs.add(studentCSV);

		CourseService courseService = new CourseService();

		assertEquals(true, courseService.sendBatchMail(studentCSVs, "CSCI5408"));

	}
}
