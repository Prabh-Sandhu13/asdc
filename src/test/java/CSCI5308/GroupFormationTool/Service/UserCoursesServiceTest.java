package CSCI5308.GroupFormationTool.Service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import CSCI5308.GroupFormationTool.Model.User;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserCoursesServiceTest {

	@Test
	public void getRoleBasedCoursesTest() {
		String emailId = "padmeshdonthu@gmail.com";

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.getRoleBasedCourses(emailId));

	}

	@Test
	public void getUserRoleByEmailIdTest() {
		String emailId = "stud@gmail.com";

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.getUserRoleByEmailId(emailId));
	}

	@Test
	public void getStudentCoursesTest() {
		String emailId = "stud@gmail.com";

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.getStudentCourses(emailId));
	}

	@Test
	public void getTACoursesTest() {
		String emailId = "stud@gmail.com";

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.getTACourses(emailId));

	}

	@Test
	public void getInstructorCoursesTest() {
		String emailId = "stud@gmail.com";

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.getInstructorCourses(emailId));

	}

	@Test
	public void getTAForCourseTest() {
		String courseId = "1";

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.getTAForCourse(courseId));
	}

	@Test
	public void enrollTAForCourseUsingEmailIdTest() {
		String courseId = "1";
		User user = new User();

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.enrollTAForCourseUsingEmailId(user, courseId));
	}

}
