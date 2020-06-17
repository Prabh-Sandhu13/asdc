package CSCI5308.GroupFormationTool.RepositoryTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Model.UserCourses;
import CSCI5308.GroupFormationTool.Repository.UserCoursesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserCoursesRepositoryTest {

    @Test
    public void getRoleBasedCoursesTest() {

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

        assertTrue(userCoursesList.get(0).getBannerId().length() < 200);
        assertTrue(userCoursesList.get(0).getCourseDescription().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseId().length() < 10);
        assertTrue(userCoursesList.get(0).getUserRole().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseName().length() < 100);

        assertFalse(userCoursesList.get(0).getBannerId() == null);
        assertFalse(userCoursesList.get(0).getCourseDescription() == null);
        assertFalse(userCoursesList.get(0).getCourseName().isEmpty());
        assertFalse(userCoursesList.get(0).getUserRole().isEmpty());
        assertFalse(userCoursesList.get(0).getCourseId().isEmpty());

        assertTrue(userCoursesList.get(0).getCourseId() instanceof String);
        assertTrue(userCoursesList.get(0).getUserRole() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseName() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseDescription() instanceof String);
        assertTrue(userCoursesList.get(0).getBannerId() instanceof String);

        assertTrue(userCoursesList.get(1).getBannerId().length() < 200);
        assertTrue(userCoursesList.get(1).getCourseDescription().length() < 100);
        assertTrue(userCoursesList.get(1).getCourseId().length() < 10);
        assertTrue(userCoursesList.get(1).getUserRole().length() < 100);
        assertTrue(userCoursesList.get(1).getCourseName().length() < 100);

        assertFalse(userCoursesList.get(1).getBannerId() == null);
        assertFalse(userCoursesList.get(1).getCourseDescription() == null);
        assertFalse(userCoursesList.get(1).getCourseName().isEmpty());
        assertFalse(userCoursesList.get(1).getUserRole().isEmpty());
        assertFalse(userCoursesList.get(1).getCourseId().isEmpty());

        assertTrue(userCoursesList.get(1).getCourseId() instanceof String);
        assertTrue(userCoursesList.get(1).getUserRole() instanceof String);
        assertTrue(userCoursesList.get(1).getCourseName() instanceof String);
        assertTrue(userCoursesList.get(1).getCourseDescription() instanceof String);
        assertTrue(userCoursesList.get(1).getBannerId() instanceof String);

        assertFalse(userCoursesList.isEmpty());
        assertTrue(userCoursesList instanceof ArrayList);

    }

    @Test
    public void getUserRoleByEmailIdTest() {

        String emailId = "padmeshdonthu@gmail.com";

        UserCoursesRepository userCoursesRepository = mock(UserCoursesRepository.class);

        when(userCoursesRepository.getUserRoleByEmailId("padmeshdonthu@gmail.com"))
                .thenReturn("Guest");
        assertTrue("Guest".equals(userCoursesRepository.getUserRoleByEmailId("padmeshdonthu@gmail.com")));
        assertFalse("TA".equals(userCoursesRepository.getUserRoleByEmailId("padmeshdonthu@gmail.com")));

        when(userCoursesRepository.getUserRoleByEmailId("ta@dal.ca"))
                .thenReturn("TA");
        assertTrue("TA".equals(userCoursesRepository.getUserRoleByEmailId("ta@dal.ca")));
        assertFalse("Instructor".equals(userCoursesRepository.getUserRoleByEmailId("ta@dal.ca")));

        when(userCoursesRepository.getUserRoleByEmailId("student@dal.ca"))
                .thenReturn("Student");
        assertTrue("Student".equals(userCoursesRepository.getUserRoleByEmailId("student@dal.ca")));
        assertFalse("TA".equals(userCoursesRepository.getUserRoleByEmailId("student@dal.ca")));

        when(userCoursesRepository.getUserRoleByEmailId("instructor@dal.ca"))
                .thenReturn("Instructor");
        assertTrue("Instructor".equals(userCoursesRepository.getUserRoleByEmailId("instructor@dal.ca")));
        assertFalse("Guest".equals(userCoursesRepository.getUserRoleByEmailId("instructor@dal.ca")));
    }

    @Test
    public void getStudentCoursesTest() {

        ArrayList<IUserCourses> userCoursesList = new ArrayList<>();
        IUserCourses userCourses = new UserCourses();

        userCourses.setBannerId("B00854462");
        userCourses.setCourseDescription("Sample description");
        userCourses.setCourseId("CSCI 5308");
        userCourses.setCourseName("Sample Text");
        userCourses.setUserRole("Student");
        userCoursesList.add(userCourses);

        assertTrue(userCoursesList.get(0).getBannerId().length() < 200);
        assertTrue(userCoursesList.get(0).getCourseDescription().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseId().length() < 10);
        assertTrue(userCoursesList.get(0).getUserRole().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseName().length() < 100);

        assertFalse(userCoursesList.get(0).getBannerId() == null);
        assertFalse(userCoursesList.get(0).getCourseDescription() == null);
        assertFalse(userCoursesList.get(0).getCourseName().isEmpty());
        assertFalse(userCoursesList.get(0).getUserRole().isEmpty());
        assertFalse(userCoursesList.get(0).getCourseId().isEmpty());

        assertTrue(userCoursesList.get(0).getCourseId() instanceof String);
        assertTrue(userCoursesList.get(0).getUserRole() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseName() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseDescription() instanceof String);
        assertTrue(userCoursesList.get(0).getBannerId() instanceof String);

        assertFalse(userCoursesList.isEmpty());
        assertTrue(userCoursesList instanceof ArrayList);
    }

    @Test
    public void getTACoursesTest() {

        ArrayList<IUserCourses> userCoursesList = new ArrayList<>();
        IUserCourses userCourses = new UserCourses();

        userCourses.setBannerId("B00854462");
        userCourses.setCourseDescription("Sample description");
        userCourses.setCourseId("CSCI 5308");
        userCourses.setCourseName("Sample Text");
        userCourses.setUserRole("TA");
        userCoursesList.add(userCourses);

        assertTrue(userCoursesList.get(0).getBannerId().length() < 200);
        assertTrue(userCoursesList.get(0).getCourseDescription().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseId().length() < 10);
        assertTrue(userCoursesList.get(0).getUserRole().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseName().length() < 100);

        assertFalse(userCoursesList.get(0).getBannerId() == null);
        assertFalse(userCoursesList.get(0).getCourseDescription() == null);
        assertFalse(userCoursesList.get(0).getCourseName().isEmpty());
        assertFalse(userCoursesList.get(0).getUserRole().isEmpty());
        assertFalse(userCoursesList.get(0).getCourseId().isEmpty());

        assertTrue(userCoursesList.get(0).getCourseId() instanceof String);
        assertTrue(userCoursesList.get(0).getUserRole() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseName() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseDescription() instanceof String);
        assertTrue(userCoursesList.get(0).getBannerId() instanceof String);

        assertFalse(userCoursesList.isEmpty());
        assertTrue(userCoursesList instanceof ArrayList);

    }

    @Test
    public void getInstructorCoursesTest() {

        ArrayList<IUserCourses> userCoursesList = new ArrayList<>();
        IUserCourses userCourses = new UserCourses();

        userCourses.setBannerId("B00854462");
        userCourses.setCourseDescription("Sample description");
        userCourses.setCourseId("CSCI 5308");
        userCourses.setCourseName("Sample Text");
        userCourses.setUserRole("Instructor");
        userCoursesList.add(userCourses);

        assertTrue(userCoursesList.get(0).getBannerId().length() < 200);
        assertTrue(userCoursesList.get(0).getCourseDescription().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseId().length() < 10);
        assertTrue(userCoursesList.get(0).getUserRole().length() < 100);
        assertTrue(userCoursesList.get(0).getCourseName().length() < 100);

        assertFalse(userCoursesList.get(0).getBannerId() == null);
        assertFalse(userCoursesList.get(0).getCourseDescription() == null);
        assertFalse(userCoursesList.get(0).getCourseName().isEmpty());
        assertFalse(userCoursesList.get(0).getUserRole().isEmpty());
        assertFalse(userCoursesList.get(0).getCourseId().isEmpty());

        assertTrue(userCoursesList.get(0).getCourseId() instanceof String);
        assertTrue(userCoursesList.get(0).getUserRole() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseName() instanceof String);
        assertTrue(userCoursesList.get(0).getCourseDescription() instanceof String);
        assertTrue(userCoursesList.get(0).getBannerId() instanceof String);

        assertFalse(userCoursesList.isEmpty());
        assertTrue(userCoursesList instanceof ArrayList);

    }

    @Test
    public void getTAForCourseTest() {

        String courseId = "CSCI 5308";
        User user = new User();
        user.setBannerId("B00854462");
        user.setEmailId("padmeshdonthu@gmail.com");
        user.setFirstName("Padmesh");
        user.setLastName("Donthu");

        assertTrue(user.getBannerId().length() < 10);
        assertTrue(user.getEmailId().length() < 100);
        assertTrue(user.getFirstName().length() < 100);
        assertTrue(user.getLastName().length() < 100);

        assertFalse(user.getLastName().isEmpty());
        assertFalse(user.getFirstName().isEmpty());
        assertFalse(user.getEmailId().isEmpty());
        assertFalse(user.getBannerId().isEmpty());

    }

    @Test
    public void enrollTAForCourseUsingEmailIdTest() {

        UserCoursesRepository userCoursesRepository = mock(UserCoursesRepository.class);
        User user = new User();
        when(userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1")).thenReturn(false);
        assertFalse(userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1"));

        when(userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1"))
                .thenReturn(true);
        assertTrue(userCoursesRepository.enrollTAForCourseUsingEmailId(user, "1"));
    }

    @Test
    public void getInstructorsForCourseTest() {
        String courseId = "CSCI 5308";
        User user = new User();
        user.setBannerId("B00854462");
        user.setEmailId("padmeshdonthu@gmail.com");
        user.setFirstName("Padmesh");
        user.setLastName("Donthu");

        assertTrue(user.getBannerId().length() < 10);
        assertTrue(user.getEmailId().length() < 100);
        assertTrue(user.getFirstName().length() < 100);
        assertTrue(user.getLastName().length() < 100);

        assertFalse(user.getLastName().isEmpty());
        assertFalse(user.getFirstName().isEmpty());
        assertFalse(user.getEmailId().isEmpty());
        assertFalse(user.getBannerId().isEmpty());
    }
}
