package CSCI5308.GroupFormationTool.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.DBMock.UserCoursesDBMock;

@SpringBootTest
public class UserCoursesTest {

	public IUserCourses createDefaultUserCourses() {
		UserCoursesDBMock userCoursesDBMock = new UserCoursesDBMock();
		IUserCourses userCourses = loadUserCourses(userCoursesDBMock);
		return userCourses;
	}

	public IUserCourses loadUserCourses(UserCoursesDBMock userCoursesDBMock) {
		IUserCourses userCourses = new UserCourses();
		userCourses = userCoursesDBMock.loadCourses(userCourses);
		return userCourses;
	}

	@Test
	public void getCourseId() {

		IUserCourses userCourses = createDefaultUserCourses();
		assertEquals("CSCI5308", userCourses.getCourseId());
	}

	@Test
	public void setId() {

		IUserCourses userCourses = new UserCourses();
		userCourses.setCourseId("CSCI5408");
		assertEquals("CSCI5408", userCourses.getCourseId());
	}

	@Test
	public void getCourseName() {

		IUserCourses userCourses = createDefaultUserCourses();
		assertEquals("Adv SDC", userCourses.getCourseName());
	}

	@Test
	public void setCourseName() {
		IUserCourses userCourses = new UserCourses();
		userCourses.setCourseName("Mobile");
		assertEquals("Mobile", userCourses.getCourseName());
	}

	@Test
	public void getCourseDescription() {

		IUserCourses userCourses = createDefaultUserCourses();
		assertEquals("sample", userCourses.getCourseDescription());
	}

	@Test
	public void setCourseDescription() {

		IUserCourses userCourses = new UserCourses();
		userCourses.setCourseDescription("example");
		assertEquals("example", userCourses.getCourseDescription());

	}

	@Test
	public void getBannerId() {

		IUserCourses userCourses = createDefaultUserCourses();
		assertEquals("B00854462", userCourses.getBannerId());
	}

	@Test
	public void setBannerId() {

		IUserCourses userCourses = new UserCourses();
		userCourses.setBannerId("B0000000");
		assertEquals("B0000000", userCourses.getBannerId());

	}

	@Test
	public void getUserRole() {

		IUserCourses userCourses = createDefaultUserCourses();
		assertEquals("student", userCourses.getUserRole());
	}

	@Test
	public void setUserRole() {

		IUserCourses userCourses = new UserCourses();
		userCourses.setUserRole("TA");
		assertEquals("TA", userCourses.getUserRole());

	}

}
