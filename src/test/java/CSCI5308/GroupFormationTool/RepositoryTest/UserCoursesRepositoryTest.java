package CSCI5308.GroupFormationTool.RepositoryTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.DBMock.UserCoursesDBMock;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.UserCoursesRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserCoursesRepositoryTest {

	private UserCoursesRepository userCoursesRepository;
	UserCoursesDBMock userCoursesDBMock = new UserCoursesDBMock();

	@Test
	public void getRoleBasedCoursesTest() {

		userCoursesRepository = mock(UserCoursesRepository.class);

		when(userCoursesRepository.getRoleBasedCourses("padmeshdonthu@gmail.com"))
				.thenReturn(new ArrayList<IUserCourses>());

		assertEquals(0, userCoursesRepository.getRoleBasedCourses("padmeshdonthu@gmail.com").size());

	}

	@Test
	public void getUserRoleByEmailIdTest() {
		userCoursesRepository = mock(UserCoursesRepository.class);

		when(userCoursesRepository.getUserRoleByEmailId("padmeshdonthu@gmail.com"))
				.thenReturn(userCoursesDBMock.getUserRoleByEmailId("padmeshdonthu@gmail.com"));
		assertEquals("Guest", userCoursesRepository.getUserRoleByEmailId("padmeshdonthu@gmail.com"));

		when(userCoursesRepository.getUserRoleByEmailId("ta@dal.ca"))
				.thenReturn(userCoursesDBMock.getUserRoleByEmailId("ta@dal.ca"));
		assertEquals("TA", userCoursesRepository.getUserRoleByEmailId("ta@dal.ca"));

		when(userCoursesRepository.getUserRoleByEmailId("student@dal.ca"))
				.thenReturn(userCoursesDBMock.getUserRoleByEmailId("student@dal.ca"));
		assertEquals("Student", userCoursesRepository.getUserRoleByEmailId("student@dal.ca"));
	}

	@Test
	public void getStudentCoursesTest() {
		userCoursesRepository = mock(UserCoursesRepository.class);

		when(userCoursesRepository.getStudentCourses("padmeshdonthu@gmail.com")).thenReturn(new ArrayList<ICourse>());

		assertEquals(0, userCoursesRepository.getStudentCourses("padmeshdonthu@gmail.com").size());

		when(userCoursesRepository.getStudentCourses("student@gmail.com"))
				.thenReturn(userCoursesDBMock.getStudentCourses("student@gmail.com"));
		assertEquals(1, userCoursesRepository.getStudentCourses("student@gmail.com").size());
	}

	@Test
	public void getTACoursesTest() {
		userCoursesRepository = mock(UserCoursesRepository.class);

		when(userCoursesRepository.getTACourses("padmeshdonthu@gmail.com")).thenReturn(new ArrayList<ICourse>());

		assertEquals(0, userCoursesRepository.getTACourses("padmeshdonthu@gmail.com").size());

		when(userCoursesRepository.getTACourses("ta@gmail.com"))
				.thenReturn(userCoursesDBMock.getTACourses("ta@gmail.com"));
		assertEquals(1, userCoursesRepository.getTACourses("ta@gmail.com").size());

	}

	@Test
	public void getInstructorCoursesTest() {
		userCoursesRepository = mock(UserCoursesRepository.class);

		when(userCoursesRepository.getInstructorCourses("padmeshdonthu@gmail.com"))
				.thenReturn(new ArrayList<ICourse>());

		assertEquals(0, userCoursesRepository.getInstructorCourses("padmeshdonthu@gmail.com").size());

		when(userCoursesRepository.getInstructorCourses("inst@gmail.com"))
				.thenReturn(userCoursesDBMock.getInstructorCourses("inst@gmail.com"));
		assertEquals(1, userCoursesRepository.getInstructorCourses("inst@gmail.com").size());

	}

	@Test
	public void getTAForCourseTest() {
		userCoursesRepository = mock(UserCoursesRepository.class);
		when(userCoursesRepository.getTAForCourse("1")).thenReturn(new ArrayList<IUser>());
		assertEquals(0, userCoursesRepository.getTAForCourse("1").size());

		when(userCoursesRepository.getTAForCourse("1")).thenReturn(userCoursesDBMock.getTAForCourse("1"));
		assertEquals(1, userCoursesRepository.getTAForCourse("1").size());
	}

	@Test
	public void enrollTAForCourseUsingEmailIdTest() {
		userCoursesRepository = mock(UserCoursesRepository.class);
		User user = new User();
		when(userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1")).thenReturn(false);
		assertEquals(false, userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1"));

		when(userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1"))
				.thenReturn(userCoursesDBMock.enrollTAForCourseUsingEmailId(user, "1"));
		assertEquals(true, userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1"));
	}

}
