package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Model.UserCourses;
import CSCI5308.GroupFormationTool.Repository.UserCoursesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserCoursesServiceTest {

    public UserCoursesRepository userCoursesRepository;
    public UserCoursesService userCoursesService;

    @BeforeEach
    public void init() {
        userCoursesRepository = mock(UserCoursesRepository.class);
        userCoursesService = new UserCoursesService();
        Injector.instance().setUserCoursesRepository(userCoursesRepository);
    }

    @Test
    void getRoleBasedCoursesTest() {
        String emailId = "tanugulia@gmail.com";
        ArrayList<IUserCourses> courseList = new ArrayList<IUserCourses>();

        IUserCourses userCourse = new UserCourses();
        userCourse.setBannerId("B00839890");
        userCourse.setCourseId("1");
        userCourse.setCourseName("Web");
        userCourse.setUserRole("admin");
        userCourse.setCourseDescription("course description");
        courseList.add(userCourse);

        when(userCoursesRepository.getRoleBasedCourses(emailId)).thenReturn(courseList);
        assertTrue(userCoursesService.getRoleBasedCourses(emailId) instanceof ArrayList);
        assertFalse(userCoursesService.getRoleBasedCourses(emailId).isEmpty());
    }

    @Test
    void getUserRoleByEmailIdTest() {
        String emailId = "stud@gmail.com";
        String role = "Guest";

        when(userCoursesRepository.getUserRoleByEmailId(emailId)).thenReturn(role);
        assertTrue(userCoursesService.getUserRoleByEmailId(emailId).equals(role));
        assertFalse(userCoursesService.getUserRoleByEmailId(emailId).isEmpty());
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
        assertTrue(userCoursesService.getStudentCourses(emailId) instanceof ArrayList);
        assertFalse(userCoursesService.getStudentCourses(emailId).isEmpty());
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
        assertTrue(userCoursesService.getTACourses(emailId) instanceof ArrayList);
        assertFalse(userCoursesService.getTACourses(emailId).isEmpty());
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
        assertTrue(userCoursesService.usersCurrentlyNotInstructorsForCourse(courseId) instanceof ArrayList);
        assertFalse(userCoursesService.usersCurrentlyNotInstructorsForCourse(courseId).isEmpty());
    }

    @Test
    void addInstructorsToCourseTest() {
        Long instructor = (long) 1;
        String courseId = "2";

        when(userCoursesRepository.addInstructorsToCourse(instructor, courseId)).thenReturn(true);
        assertTrue(userCoursesService.addInstructorsToCourse(instructor, courseId));

        String invcourseId = "";
        when(userCoursesRepository.addInstructorsToCourse(instructor, invcourseId)).thenReturn(false);
        assertFalse(userCoursesService.addInstructorsToCourse(instructor, invcourseId));
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
        assertTrue(userCoursesService.getInstructorCourses(emailId) instanceof ArrayList);
        assertFalse(userCoursesService.getInstructorCourses(emailId).isEmpty());
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
        assertTrue(userCoursesService.getTAForCourse(courseId) instanceof ArrayList);
        assertFalse(userCoursesService.getTAForCourse(courseId).isEmpty());
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
        assertTrue(userCoursesService.enrollTAForCourseUsingEmailId(user, courseId));

        User existingUser = new User();
        existingUser.setBannerId("B00839890");
        existingUser.setEmailId("tn300318@dal.ca");
        existingUser.setFirstName("tanu");
        existingUser.setLastName("gulia");
        existingUser.setId(2);
        when(userCoursesRepository.enrollTAForCourseUsingEmailId(existingUser, courseId)).thenReturn(false);
        assertFalse(userCoursesService.enrollTAForCourseUsingEmailId(existingUser, courseId));
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
        assertTrue(userCoursesService.getInstructorsForCourse(courseId) instanceof ArrayList);
        assertFalse(userCoursesService.getInstructorsForCourse(courseId).isEmpty());
    }
}