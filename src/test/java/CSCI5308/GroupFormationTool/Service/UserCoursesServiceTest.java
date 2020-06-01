package CSCI5308.GroupFormationTool.Service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.Model.UserCourses;
import CSCI5308.GroupFormationTool.Repository.UserCoursesRepository;

@SpringBootTest
public class UserCoursesServiceTest {

	@Test
	public void getRoleBasedCoursesTest() {
		String emailId = "padmeshdonthu@gmail.com";

		UserCoursesRepository userCoursesRepository = mock(UserCoursesRepository.class);

		UserCoursesService userCoursesService = new UserCoursesService();

		when(userCoursesRepository.getRoleBasedCourses(emailId)).thenReturn(new ArrayList<IUserCourses>());

		assertEquals(1, userCoursesService.getRoleBasedCourses(emailId).size());

	}

}
