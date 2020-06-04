package CSCI5308.GroupFormationTool.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.DBMock.CourseDBMock;
import CSCI5308.GroupFormationTool.Model.Course;

@SpringBootTest
public class CourseRepositoryTest {

	private CourseDBMock courseDBMock;

	@Test
	public void getAllCoursesTest() {

		courseDBMock = new CourseDBMock();

		ArrayList<ICourse> courses = courseDBMock.getAllCourses();

		assertEquals(1, courses.size());
	}

	@Test
	public void createCourse() {
		courseDBMock = new CourseDBMock();

		ICourse course = new Course();

		course = courseDBMock.loadCourses(course);

		assertEquals(true, courseDBMock.createCourse(course));
	}

	@Test
	public void deleteCourse() {
		courseDBMock = new CourseDBMock();
		assertEquals(true, courseDBMock.deleteCourse("1"));
	}

}
