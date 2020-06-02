package CSCI5308.GroupFormationTool.Model;

import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.DBMock.CourseDBMock;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class CourseTest {

	public ICourse createDefaultCourse() {
		CourseDBMock courseDBMock = new CourseDBMock();
		ICourse course = loadCourse(courseDBMock);
		return course;
	}

	public ICourse loadCourse(CourseDBMock courseDBMock) {
		ICourse course = new Course();
		course = courseDBMock.loadCourses(course);
		return course;
	}

	@Test
	public void getId() {

		ICourse course = createDefaultCourse();
		assertEquals("CSCI5308", course.getId());
	}

	@Test
	public void setId() {

		ICourse course = new Course();
		course.setId("CSCI5408");
		assertEquals("CSCI5408", course.getId());
	}

	@Test
	public void getName() {

		ICourse course = createDefaultCourse();
		assertEquals("Adv SDC", course.getName());
	}

	@Test
	public void setName() {
		ICourse course = new Course();
		course.setName("Mobile");
		assertEquals("Mobile", course.getName());
	}

	@Test
	public void getCredits() {

		ICourse course = createDefaultCourse();
		assertEquals(3, course.getCredits());
	}

	@Test
	public void setCredits() {

		ICourse course = new Course();
		course.setCredits(5);
		assertEquals(5, course.getCredits());
	}

	@Test
	public void getDescription() {

		ICourse course = createDefaultCourse();
		assertEquals("sample", course.getDescription());
	}

	@Test
	public void setDescription() {

		ICourse course = new Course();
		course.setDescription("example");
		assertEquals("example", course.getDescription());

	}

}
