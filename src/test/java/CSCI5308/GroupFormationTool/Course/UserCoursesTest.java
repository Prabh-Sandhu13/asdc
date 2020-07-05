package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserCoursesTest {

    public UserCoursesRepository userCoursesRepository;
    IUserCourses userCourses = new UserCourses();

    @BeforeEach
    public void init() {
        userCoursesRepository = mock(UserCoursesRepository.class);
        Injector.instance().setUserCoursesRepository(userCoursesRepository);
    }

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

    @Test
    void getUserRoleByEmailIdTest() {
        String emailId = "stud@gmail.com";
        String role = "Guest";
        when(userCoursesRepository.getUserRoleByEmailId(emailId)).thenReturn(role);
        assertTrue(userCourses.getUserRoleByEmailId(emailId).equals(role));
        assertFalse(userCourses.getUserRoleByEmailId(emailId).isEmpty());
    }

    @Test
    void getStudentCoursesTest() {
        String emailId = "stud@gmail.com";
        ArrayList<ICourse> studentCourseList = new ArrayList<ICourse>();
        ICourse course = new Course();
        course.setId("1");
        course.setName("Web");
        course.setDescription("description");
        course.setCredits(4);
        studentCourseList.add(course);
        when(userCoursesRepository.getStudentCourses(emailId)).thenReturn(studentCourseList);
        assertTrue(userCourses.getStudentCourses(emailId) instanceof ArrayList);
        assertFalse(userCourses.getStudentCourses(emailId).isEmpty());
    }

    @Test
    void getTACoursesTest() {
        String emailId = "stud@gmail.com";
        ArrayList<ICourse> taCourseList = new ArrayList<ICourse>();
        ICourse course = new Course();
        course.setId("1");
        course.setName("Web");
        course.setDescription("description");
        course.setCredits(4);
        taCourseList.add(course);
        when(userCoursesRepository.getTACourses(emailId)).thenReturn(taCourseList);
        assertTrue(userCourses.getTACourses(emailId) instanceof ArrayList);
        assertFalse(userCourses.getTACourses(emailId).isEmpty());
    }

    @Test
    void usersCurrentlyNotInstructorsForCourseTest() {
        String courseId = "1";
        ArrayList<IUser> userList = new ArrayList<IUser>();
        IUser user = new User();
        user.setBannerId("B00839890");
        user.setEmailId("tn300318@dal.ca");
        user.setFirstName("tanu");
        user.setLastName("gulia");
        user.setId(2);
        userList.add(user);
        when(userCoursesRepository.usersCurrentlyNotInstructorsForCourse(courseId)).thenReturn(userList);
        assertTrue(userCourses.usersCurrentlyNotInstructorsForCourse(courseId) instanceof ArrayList);
        assertFalse(userCourses.usersCurrentlyNotInstructorsForCourse(courseId).isEmpty());
    }

    @Test
    void addInstructorsToCourseTest() {
        Long instructor = (long) 1;
        String courseId = "2";
        when(userCoursesRepository.addInstructorsToCourse(instructor, courseId)).thenReturn(true);
        assertTrue(userCourses.addInstructorsToCourse(instructor, courseId));
        String invcourseId = "";
        when(userCoursesRepository.addInstructorsToCourse(instructor, invcourseId)).thenReturn(false);
        assertFalse(userCourses.addInstructorsToCourse(instructor, invcourseId));
    }

    @Test
    void getInstructorCoursesTest() {
        String emailId = "stud@gmail.com";
        ArrayList<ICourse> instructorCourseList = new ArrayList<ICourse>();
        ICourse course = new Course();
        course.setId("1");
        course.setName("Web");
        course.setDescription("description");
        course.setCredits(4);
        instructorCourseList.add(course);
        when(userCoursesRepository.getInstructorCourses(emailId)).thenReturn(instructorCourseList);
        assertTrue(userCourses.getInstructorCourses(emailId) instanceof ArrayList);
        assertFalse(userCourses.getInstructorCourses(emailId).isEmpty());
    }

    @Test
    void getTAForCourseTest() {
        String courseId = "1";
        ArrayList<IUser> taList = new ArrayList<IUser>();
        IUser user = new User();
        user.setBannerId("B00839890");
        user.setEmailId("tn300318@dal.ca");
        user.setFirstName("tanu");
        user.setLastName("gulia");
        user.setId(2);
        taList.add(user);
        when(userCoursesRepository.getTAForCourse(courseId)).thenReturn(taList);
        assertTrue(userCourses.getTAForCourse(courseId) instanceof ArrayList);
        assertFalse(userCourses.getTAForCourse(courseId).isEmpty());
    }

    @Test
    void enrollTAForCourseUsingEmailIdTest() {
        String courseId = "1";
        User user = new User();
        user.setBannerId("B00839890");
        user.setEmailId("tn300318@dal.ca");
        user.setFirstName("tanu");
        user.setLastName("gulia");
        user.setId(2);
        when(userCoursesRepository.enrollTAForCourseUsingEmailId(user, courseId)).thenReturn(true);
        assertTrue(userCourses.enrollTAForCourseUsingEmailId(user, courseId));
        User existingUser = new User();
        existingUser.setBannerId("B00839890");
        existingUser.setEmailId("tn300318@dal.ca");
        existingUser.setFirstName("tanu");
        existingUser.setLastName("gulia");
        existingUser.setId(2);
        when(userCoursesRepository.enrollTAForCourseUsingEmailId(existingUser, courseId)).thenReturn(false);
        assertFalse(userCourses.enrollTAForCourseUsingEmailId(existingUser, courseId));
    }

    @Test
    void getInstructorsForCourseTest() {
        String courseId = "1";
        ArrayList<IUser> instructorList = new ArrayList<IUser>();
        IUser user = new User();
        user.setBannerId("B00839890");
        user.setEmailId("tn300318@dal.ca");
        user.setFirstName("tanu");
        user.setLastName("gulia");
        user.setId(2);
        instructorList.add(user);
        when(userCoursesRepository.getInstructorsForCourse(courseId)).thenReturn(instructorList);
        assertTrue(userCourses.getInstructorsForCourse(courseId) instanceof ArrayList);
        assertFalse(userCourses.getInstructorsForCourse(courseId).isEmpty());
    }

}
