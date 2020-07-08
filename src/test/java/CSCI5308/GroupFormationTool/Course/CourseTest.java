package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Course.Course;
import CSCI5308.GroupFormationTool.Course.ICourse;
import CSCI5308.GroupFormationTool.Question.QuestionAdminRepository;
import CSCI5308.GroupFormationTool.Question.QuestionManagerRepository;
import CSCI5308.GroupFormationTool.Course.CourseDBMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

@SpringBootTest
public class CourseTest {

	public CourseRepository courseRepository;
	
	@BeforeEach
    public void init() {
		courseRepository = mock(CourseRepository.class);
        Injector.instance().setCourseRepository(courseRepository);
    }
	
    public ICourse createDefaultCourse() {
        CourseDBMock courseDBMock = new CourseDBMock();
        ICourse course = loadCourse(courseDBMock);
        return course;
    }

    public ICourse loadCourse(CourseDBMock courseDBMock) {
        ICourse course = new Course();
        course = courseDBMock.loadCourses(course);
        return course;
    }

    @Test
    public void getIdTest() {
        ICourse course = createDefaultCourse();
        assertEquals("CSCI5308", course.getId());
    }

    @Test
    public void setIdTest() {
        ICourse course = new Course();
        course.setId("CSCI5408");
        assertEquals("CSCI5408", course.getId());
    }

    @Test
    public void getNameTest() {

        ICourse course = createDefaultCourse();
        assertEquals("Adv SDC", course.getName());
    }

    @Test
    public void setNameTest() {
        ICourse course = new Course();
        course.setName("Mobile");
        assertEquals("Mobile", course.getName());
    }

    @Test
    public void getCreditsTest() {
        ICourse course = createDefaultCourse();
        assertEquals(3, course.getCredits());
    }

    @Test
    public void setCreditsTest() {
        ICourse course = new Course();
        course.setCredits(5);
        assertEquals(5, course.getCredits());
    }

    @Test
    public void getDescriptionTest() {
        ICourse course = createDefaultCourse();
        assertEquals("sample", course.getDescription());
    }

    @Test
    public void setDescriptionTest() {
        ICourse course = new Course();
        course.setDescription("example");
        assertEquals("example", course.getDescription());

    }

    @Test
    void getAllCoursesTest() {
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
        assertTrue(course.getAllCourses().size() == 2);
        assertFalse(course.getAllCourses().isEmpty());
        courses = new ArrayList<>();
        when(courseRepository.getAllCourses()).thenReturn(courses);
        assertTrue(course.getAllCourses().isEmpty());
        assertFalse(course.getAllCourses().size() > 1);
    }

    @Test
    void getCourseByIdTest() {
        String courseId = "CSCI 5000";
        ICourse course = new Course();
        course.setName("Course 1");
        course.setCredits(3);
        course.setId(courseId);
        course.setDescription("Course 1 description");
        when(courseRepository.getCourseById(courseId)).thenReturn(course);
        assertTrue(course.getCourseById(courseId).getId().equals(courseId));
        assertFalse(course.getCourseById(courseId).getCredits() == 0);
        courseId = "CSCI 9182";
        course = new Course();
        when(courseRepository.getCourseById(courseId)).thenReturn(course);
        assertFalse(courseId.equals(course.getCourseById(courseId).getId()));
        assertTrue(course.getCourseById(courseId).getDescription() == null);
    }

    @Test
    void createCourseTest() {
        ICourse course = new Course();
        course.setName("Course 1");
        course.setCredits(3);
        course.setId("CSCI 5200");
        course.setDescription("Course 1 description");
        when(courseRepository.createCourse(course)).thenReturn(true);
        assertTrue(course.createCourse());
        course = new Course();
        course.setName("Course 1");
        course.setCredits(3);
        course.setId("CSCI 5200");
        course.setDescription("Course 1 description");
        when(courseRepository.createCourse(course)).thenReturn(false);
        assertFalse(course.createCourse());
    }

    @Test
    void deleteCourseTest() {
        String courseId = "CSCI 5100";
        UserCourses userCourses = new UserCourses();
        ICourse course = new Course();
        userCourses.setBannerId("B00854462");
        userCourses.setCourseDescription("Sample");
        userCourses.setCourseId(courseId);
        userCourses.setCourseName("New Course");
        when(courseRepository.deleteCourse(courseId)).thenReturn(userCourses.getCourseId().equals(courseId));
        assertTrue(course.deleteCourse(courseId));
        courseId = "CSCI 3220";
        userCourses = new UserCourses();
        when(courseRepository.deleteCourse(courseId)).thenReturn(courseId.equals(userCourses.getCourseId()));
        assertFalse(course.deleteCourse(courseId));
    }
}
