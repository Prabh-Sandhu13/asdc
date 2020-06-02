package CSCI5308.GroupFormationTool.Service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CourseServiceTest {

	@Test
	public void getAllCoursesTest() {
		
		CourseService courseService = new CourseService();
		
		assertNotEquals(null, courseService.getAllCourses());
	}
}
