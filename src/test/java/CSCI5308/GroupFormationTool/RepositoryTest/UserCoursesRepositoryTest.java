package CSCI5308.GroupFormationTool.RepositoryTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.Model.UserCourses;
import CSCI5308.GroupFormationTool.Repository.UserCoursesRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserCoursesRepositoryTest {

	private UserCoursesRepository userCoursesRepository;

	@Test
	public void getRoleBasedCoursesTest() {

		userCoursesRepository = mock(UserCoursesRepository.class);

		when(userCoursesRepository.getRoleBasedCourses("padmeshdonthu@gmail.com"))
				.thenReturn(new ArrayList<IUserCourses>());

		assertEquals(0, userCoursesRepository.getRoleBasedCourses("padmeshdonthu@gmail.com").size());

	}

}
