package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Course.IUserCourses;
import CSCI5308.GroupFormationTool.Course.UserCourses;
import CSCI5308.GroupFormationTool.Course.UserCoursesDBMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void getCourseIdTest() {
        IUserCourses userCourses = createDefaultUserCourses();
        assertEquals("CSCI5308", userCourses.getCourseId());
    }

    @Test
    public void setIdTest() {
        IUserCourses userCourses = new UserCourses();
        userCourses.setCourseId("CSCI5408");
        assertEquals("CSCI5408", userCourses.getCourseId());
    }

    @Test
    public void getCourseNameTest() {
        IUserCourses userCourses = createDefaultUserCourses();
        assertEquals("Adv SDC", userCourses.getCourseName());
    }

    @Test
    public void setCourseNameTest() {
        IUserCourses userCourses = new UserCourses();
        userCourses.setCourseName("Mobile");
        assertEquals("Mobile", userCourses.getCourseName());
    }

    @Test
    public void getCourseDescriptionTest() {
        IUserCourses userCourses = createDefaultUserCourses();
        assertEquals("sample", userCourses.getCourseDescription());
    }

    @Test
    public void setCourseDescriptionTest() {
        IUserCourses userCourses = new UserCourses();
        userCourses.setCourseDescription("example");
        assertEquals("example", userCourses.getCourseDescription());
    }

    @Test
    public void getBannerIdTest() {
        IUserCourses userCourses = createDefaultUserCourses();
        assertEquals("B00854462", userCourses.getBannerId());
    }

    @Test
    public void setBannerIdTest() {
        IUserCourses userCourses = new UserCourses();
        userCourses.setBannerId("B0000000");
        assertEquals("B0000000", userCourses.getBannerId());
    }

    @Test
    public void getUserRoleTest() {
        IUserCourses userCourses = createDefaultUserCourses();
        assertEquals("student", userCourses.getUserRole());
    }

    @Test
    public void setUserRoleTest() {
        IUserCourses userCourses = new UserCourses();
        userCourses.setUserRole("TA");
        assertEquals("TA", userCourses.getUserRole());
    }

}
