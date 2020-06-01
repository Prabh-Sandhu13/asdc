package CSCI5308.GroupFormationTool.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Repository.CourseRepository;

@SpringBootTest
public class CourseServiceTest {

	@Test
	public void getAllCoursesTest() {

		CourseRepository courseRepository = mock(CourseRepository.class);
		CourseService courseService = new CourseService();

		when(courseRepository.getAllCourses()).thenReturn(new ArrayList<ICourse>());

		assertEquals(true, courseService.getAllCourses().size() > 1);

	}
}
