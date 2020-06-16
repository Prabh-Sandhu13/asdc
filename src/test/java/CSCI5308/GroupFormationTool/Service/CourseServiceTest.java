package CSCI5308.GroupFormationTool.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.DBMock.CourseDBMock;
import CSCI5308.GroupFormationTool.Model.Course;

@SpringBootTest
public class CourseServiceTest {

	@Test
	public void getAllCoursesTest() {

		CourseService courseService = new CourseService();

		assertNotEquals(null, courseService.getAllCourses());
	}

	@Test
	public void getCourseByIdTest() {

		CourseService courseService = new CourseService();

		assertNotEquals(null, courseService.getCourseById("1"));
	}

	@Test
	public void deleteCourseTest() {

		CourseService courseService = new CourseService();

		assertNotEquals(false, courseService.deleteCourse("00000000000"));
	}

	@Test
	public void createCourseTest() {
		CourseService courseService = new CourseService();

		CourseDBMock courseDBMock = new CourseDBMock();

		ICourse course = new Course();
		course = courseDBMock.loadCourses(course);

		assertNotEquals(null, courseService.createCourse(course));
	}
}
