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
public class UserCoursesServiceTest {

    public UserCoursesService userCoursesService;
    public UserCoursesRepository userCoursesRepository;

    @BeforeEach
    public void init() {
        userCoursesService = new UserCoursesService();
        userCoursesRepository = mock(UserCoursesRepository.class);
        Injector.instance().setUserCoursesRepository(userCoursesRepository);
    }

    @Test
    void getRoleBasedCourses() {
        String emailId = "padmeshdonthu1996@gmail.com";
        when(userCoursesRepository.getRoleBasedCourses(emailId)).thenReturn(new ArrayList<>());
        assertFalse(userCoursesService.getRoleBasedCourses(emailId).size() > 1);

        emailId = "padmeshdonthu@gmail.com";
        ArrayList<IUserCourses> userCoursesList = new ArrayList<>();
        IUserCourses userCourses = new UserCourses();

        userCourses.setBannerId("B00854462");
        userCourses.setCourseDescription("Sample description");
        userCourses.setCourseId("CSCI 5308");
        userCourses.setCourseName("Sample Text");
        userCourses.setUserRole("Student");
        userCoursesList.add(userCourses);

        userCourses.setBannerId("B00854462");
        userCourses.setCourseDescription("Sample description 2");
        userCourses.setCourseId("CSCI 5309");
        userCourses.setCourseName("Sample Text 2");
        userCourses.setUserRole("Student");
        userCoursesList.add(userCourses);

        when(userCoursesRepository.getRoleBasedCourses(emailId)).thenReturn(userCoursesList);
        assertTrue(userCoursesService.getRoleBasedCourses(emailId).size() == 2);

    }

    @Test
    void getUserRoleByEmailId() {
        when(userCoursesRepository.getUserRoleByEmailId("padmeshdonthu@gmail.com"))
                .thenReturn("Guest");
        assertTrue("Guest".equals(userCoursesService.getUserRoleByEmailId("padmeshdonthu@gmail.com")));
        assertFalse("TA".equals(userCoursesService.getUserRoleByEmailId("padmeshdonthu@gmail.com")));

        when(userCoursesRepository.getUserRoleByEmailId("ta@dal.ca"))
                .thenReturn("TA");
        assertTrue("TA".equals(userCoursesService.getUserRoleByEmailId("ta@dal.ca")));
        assertFalse("Instructor".equals(userCoursesService.getUserRoleByEmailId("ta@dal.ca")));

        when(userCoursesRepository.getUserRoleByEmailId("student@dal.ca"))
                .thenReturn("Student");
        assertTrue("Student".equals(userCoursesService.getUserRoleByEmailId("student@dal.ca")));
        assertFalse("TA".equals(userCoursesService.getUserRoleByEmailId("student@dal.ca")));

        when(userCoursesRepository.getUserRoleByEmailId("instructor@dal.ca"))
                .thenReturn("Instructor");
        assertTrue("Instructor".equals(userCoursesService.getUserRoleByEmailId("instructor@dal.ca")));
        assertFalse("Guest".equals(userCoursesService.getUserRoleByEmailId("instructor@dal.ca")));

    }

    @Test
    void getStudentCourses() {
        String emailId = "padmeshdonthu@gmail.com";
        ArrayList<ICourse> courseList = new ArrayList<>();
        ICourse course = new Course();
        course.setName("New Course");
        course.setCredits(3);
        course.setDescription("New course description");
        course.setId("CSCI 5308");
        courseList.add(course);

        course = new Course();
        course.setName("New Course 2");
        course.setCredits(4);
        course.setDescription("New course description 2");
        course.setId("CSCI 5408");
        courseList.add(course);

        when(userCoursesRepository.getStudentCourses(emailId)).thenReturn(courseList);
        assertTrue(userCoursesService.getStudentCourses(emailId).size() == 2);

        emailId = "padmeshdonthu1996@gmail.com";
        when(userCoursesRepository.getStudentCourses(emailId)).thenReturn(null);
        assertFalse(userCoursesService.getStudentCourses(emailId) instanceof ArrayList);

    }

    @Test
    void getTACourses() {
        String emailId = "padmeshdonthu@gmail.com";
        ArrayList<ICourse> courseList = new ArrayList<>();
        ICourse course = new Course();
        course.setName("New Course");
        course.setCredits(3);
        course.setDescription("New course description");
        course.setId("CSCI 5308");
        courseList.add(course);

        course = new Course();
        course.setName("New Course 2");
        course.setCredits(4);
        course.setDescription("New course description 2");
        course.setId("CSCI 5408");
        courseList.add(course);

        when(userCoursesRepository.getTACourses(emailId)).thenReturn(courseList);
        assertTrue(userCoursesService.getTACourses(emailId).size() == 2);

        emailId = "padmeshdonthu1996@gmail.com";
        when(userCoursesRepository.getTACourses(emailId)).thenReturn(null);
        assertFalse(userCoursesService.getTACourses(emailId) instanceof ArrayList);
    }

    @Test
    void usersCurrentlyNotInstructorsForCourse() {
        String courseId = "CSCI 5308";
        ArrayList<IUser> users = new ArrayList<>();

        IUser user = new User();
        user.setBannerId("B00854462");
        user.setEmailId("padmeshdonthu@gmail.com");
        user.setFirstName("Padmesh");
        user.setLastName("Donthu");
        users.add(user);

        when(userCoursesRepository.usersCurrentlyNotInstructorsForCourse(courseId)).thenReturn(users);
        assertFalse(userCoursesService.usersCurrentlyNotInstructorsForCourse(courseId).isEmpty());
        assertTrue(userCoursesService.usersCurrentlyNotInstructorsForCourse(courseId).size() == 1);
    }

    @Test
    void addInstructorsToCourse() {

        when(userCoursesRepository.addInstructorsToCourse((long) 1, "CSCI 5308")).thenReturn(true);
        assertTrue(userCoursesService.addInstructorsToCourse((long) 1, "CSCI 5308"));

        when(userCoursesRepository.addInstructorsToCourse((long) 2, "CSCI 5308")).thenReturn(false);
        assertFalse(userCoursesService.addInstructorsToCourse((long) 2, "CSCI 5308"));

    }

    @Test
    void getInstructorCourses() {
        String emailId = "padmeshdonthu@gmail.com";
        ArrayList<ICourse> courseList = new ArrayList<>();
        ICourse course = new Course();
        course.setName("New Course");
        course.setCredits(3);
        course.setDescription("New course description");
        course.setId("CSCI 5308");

        courseList.add(course);

        course = new Course();
        course.setName("New Course 2");
        course.setCredits(4);
        course.setDescription("New course description 2");
        course.setId("CSCI 5408");
        courseList.add(course);

        when(userCoursesRepository.getInstructorCourses(emailId)).thenReturn(courseList);
        assertTrue(userCoursesService.getInstructorCourses(emailId).size() == 2);

        emailId = "padmeshdonthu1996@gmail.com";
        when(userCoursesRepository.getInstructorCourses(emailId)).thenReturn(null);
        assertFalse(userCoursesService.getInstructorCourses(emailId) instanceof ArrayList);

    }

    @Test
    void getTAForCourse() {
        String courseId = "CSCI 5308";
        ArrayList<IUser> users = new ArrayList<>();

        IUser user = new User();
        user.setBannerId("B00854462");
        user.setEmailId("padmeshdonthu@gmail.com");
        user.setFirstName("Padmesh");
        user.setLastName("Donthu");
        users.add(user);

        when(userCoursesRepository.getTAForCourse(courseId)).thenReturn(users);
        assertFalse(userCoursesService.getTAForCourse(courseId).isEmpty());
        assertTrue(userCoursesService.getTAForCourse(courseId).size() == 1);
    }

    @Test
    void enrollTAForCourseUsingEmailId() {
        User user = new User();
        user.setBannerId("B00854462");
        user.setEmailId("padmeshdonthu@gmail.com");
        user.setFirstName("Padmesh");
        user.setLastName("Donthu");
        String courseId = "CSCI 5308";
        when(userCoursesRepository.enrollTAForCourseUsingEmailId(user, courseId)).thenReturn(true);
        assertTrue(userCoursesService.enrollTAForCourseUsingEmailId(user, courseId));

        when(userCoursesRepository.enrollTAForCourseUsingEmailId(user, courseId)).thenReturn(false);
        assertFalse(userCoursesService.enrollTAForCourseUsingEmailId(user, courseId));

    }

    @Test
    void getInstructorsForCourse() {
        String courseId = "CSCI 5308";
        ArrayList<IUser> users = new ArrayList<>();

        IUser user = new User();
        user.setBannerId("B00854462");
        user.setEmailId("padmeshdonthu@gmail.com");
        user.setFirstName("Padmesh");
        user.setLastName("Donthu");
        users.add(user);

        when(userCoursesRepository.getInstructorsForCourse(courseId)).thenReturn(users);
        assertFalse(userCoursesService.getInstructorsForCourse(courseId).isEmpty());
        assertTrue(userCoursesService.getInstructorsForCourse(courseId).size() == 1);
    }
}
