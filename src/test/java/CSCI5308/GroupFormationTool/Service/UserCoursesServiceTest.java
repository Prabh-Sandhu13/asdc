package CSCI5308.GroupFormationTool.Service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserCoursesServiceTest {

	@Test
	public void getRoleBasedCoursesTest() {
		String emailId = "padmeshdonthu@gmail.com";

		UserCoursesService userCoursesService = new UserCoursesService();

		assertNotEquals(null, userCoursesService.getRoleBasedCourses(emailId));

	}

}
