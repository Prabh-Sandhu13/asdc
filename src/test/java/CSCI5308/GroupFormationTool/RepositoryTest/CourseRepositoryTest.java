package CSCI5308.GroupFormationTool.RepositoryTest;

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
public class CourseRepositoryTest {

	private CourseRepository courseRepository;

	@Test
	public void getAllCoursesTest() {

		courseRepository = mock(CourseRepository.class);

		when(courseRepository.getAllCourses()).thenReturn(new ArrayList<ICourse>());

		assertEquals(0, courseRepository.getAllCourses().size());

	}
}
