package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.UserCourses;
import CSCI5308.GroupFormationTool.Repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseServiceTest {

    public CourseRepository courseRepository;
    public CourseService courseService;

    @BeforeEach
    public void init() {
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseService();
        Injector.instance().setCourseRepository(courseRepository);
    }

    @Test
    void getAllCourses() {
        ArrayList<ICourse> courses = new ArrayList<>();
        ICourse course = new Course();
        course.setName("Course 1");
        course.setCredits(3);
        course.setId("CSCI 5000");
        course.setDescription("Course 1 description");
        courses.add(course);

        course = new Course();
        course.setName("Course 2");
        course.setCredits(4);
        course.setId("CSCI 6000");
        course.setDescription("Course 2 description");
        courses.add(course);

        when(courseRepository.getAllCourses()).thenReturn(courses);
        assertTrue(courseService.getAllCourses() instanceof ArrayList);
        assertFalse(courseService.getAllCourses().isEmpty());

        courses = new ArrayList<>();

        when(courseRepository.getAllCourses()).thenReturn(courses);
        assertTrue(courseService.getAllCourses().isEmpty());
        assertFalse(courseService.getAllCourses().size() > 1);

    }

    @Test
    void getCourseById() {
        String courseId = "CSCI 5000";
        ICourse course = new Course();
        course.setName("Course 1");
        course.setCredits(3);
        course.setId(courseId);
        course.setDescription("Course 1 description");

        when(courseRepository.getCourseById(courseId)).thenReturn(course);
        assertTrue(courseService.getCourseById(courseId).getId().equals(courseId));
        assertFalse(courseService.getCourseById(courseId).getCredits() == 0);

        courseId = "CSCI 9182";
        course = new Course();
        when(courseRepository.getCourseById(courseId)).thenReturn(course);
        assertFalse(courseId.equals(courseService.getCourseById(courseId).getId()));
        assertTrue(courseService.getCourseById(courseId).getDescription() == null);

    }

    @Test
    void createCourse() {
        ICourse course = new Course();
        course.setName("Course 1");
        course.setCredits(3);
        course.setId("CSCI 5200");
        course.setDescription("Course 1 description");

        when(courseRepository.createCourse(course)).thenReturn(true);
        assertTrue(courseService.createCourse(course));

        course = new Course();
        course.setName("Course 1");
        course.setCredits(3);
        course.setId("CSCI 5200");
        course.setDescription("Course 1 description");

        when(courseRepository.createCourse(course)).thenReturn(false);
        assertFalse(courseService.createCourse(course));
    }

    @Test
    void deleteCourse() {
        String courseId = "CSCI 5100";
        UserCourses userCourses = new UserCourses();
        userCourses.setBannerId("B00854462");
        userCourses.setCourseDescription("Sample");
        userCourses.setCourseId(courseId);
        userCourses.setCourseName("New Course");

        when(courseRepository.deleteCourse(courseId)).thenReturn(userCourses.getCourseId().equals(courseId));
        assertTrue(courseService.deleteCourse(courseId));

        courseId = "CSCI 3220";
        userCourses = new UserCourses();
        when(courseRepository.deleteCourse(courseId)).thenReturn(courseId.equals(userCourses.getCourseId()));
        assertFalse(courseService.deleteCourse(courseId));

    }
}
