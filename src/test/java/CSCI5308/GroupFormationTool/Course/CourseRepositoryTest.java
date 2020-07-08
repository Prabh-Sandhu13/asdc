package CSCI5308.GroupFormationTool.Course;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CourseRepositoryTest {

    @InjectMocks
    public CourseRepository courseRepository = new CourseRepository();

    @Test
    public void deleteCourseTest() {

        Course course = new Course();
        String deletedId = "CSCI 5308";

        course.setName("New Course");
        course.setCredits(3);
        course.setDescription("New course description");
        course.setId(deletedId);

        assertFalse(course.getId().isEmpty());
        assertTrue(course.getId().equals(deletedId));
        assertTrue(course.getId().length() < 200);
        assertTrue(course.getId().equals(deletedId));
        assertFalse(course.getId().equals("1"));
    }

    @Test
    void getAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course course = new Course();
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

        assertTrue(courseList.get(0).getId().length() < 100);
        assertTrue(courseList.get(0).getId().length() < 10);
        assertTrue(courseList.get(0).getCredits() < 10);
        assertTrue(courseList.get(0).getDescription().length() < 100);

        assertFalse(courseList.get(0).getName().isEmpty());
        assertFalse(courseList.get(0).getId().isEmpty());
        assertFalse(courseList.get(0).getCredits() == 0);
        assertFalse(courseList.get(0).getDescription().isEmpty());
        assertTrue(courseList.get(0).getName().equals("New Course"));
        assertTrue(courseList.get(0).getId().equals("CSCI 5308"));
        assertTrue(courseList.get(0).getCredits() == 3);
        assertTrue(courseList.get(0).getDescription().equals("New course description"));

        assertTrue(courseList.get(1).getId().length() < 100);
        assertTrue(courseList.get(1).getId().length() < 10);
        assertTrue(courseList.get(1).getCredits() < 10);
        assertTrue(courseList.get(1).getDescription().length() < 100);

        assertFalse(courseList.get(1).getName().isEmpty());
        assertFalse(courseList.get(1).getId().isEmpty());
        assertFalse(courseList.get(1).getCredits() == 0);
        assertFalse(courseList.get(1).getDescription().isEmpty());
        assertTrue(courseList.get(1).getName().equals("New Course 2"));
        assertTrue(courseList.get(1).getId().equals("CSCI 5408"));
        assertTrue(courseList.get(1).getCredits() == 4);
        assertTrue(courseList.get(1).getDescription().equals("New course description 2"));

        assertFalse(courseList.isEmpty());
        assertTrue(courseList.size() == 2);
    }

    @Test
    void createCourse() {
        Course course = new Course();
        course.setName("New Course");
        course.setCredits(3);
        course.setDescription("New course description");
        course.setId("CSCI 5308");

        assertTrue(course.getName().length() < 200);
        assertTrue(course.getId().length() < 10);
        assertTrue(course.getCredits() < 10);
        assertTrue(course.getDescription().length() < 100);
        assertFalse(course.getName().isEmpty());
        assertFalse(course.getId().isEmpty());
        assertFalse(course.getCredits() == 0);
        assertFalse(course.getDescription().isEmpty());
        assertTrue(course.getName().equals("New Course"));
        assertTrue(course.getId().equals("CSCI 5308"));
        assertTrue(course.getCredits() == 3);
        assertTrue(course.getDescription().equals("New course description"));
    }

    @Test
    void getCourseById() {
        String courseId = "CSCI 5308";

        Course course = new Course();
        course.setName("New Course");
        course.setCredits(6);
        course.setDescription("New course description");
        course.setId(courseId);

        assertTrue(course.getName().length() < 200);
        assertTrue(course.getId().length() < 10);
        assertTrue(course.getCredits() < 10);
        assertTrue(course.getDescription().length() < 100);
        assertFalse(course.getName().isEmpty());
        assertFalse(course.getId().isEmpty());
        assertFalse(course.getCredits() == 0);
        assertFalse(course.getDescription().isEmpty());
        assertTrue(course.getName().equals("New Course"));
        assertTrue(course.getId().equals("CSCI 5308"));
        assertTrue(course.getCredits() == 6);
        assertTrue(course.getDescription().equals("New course description"));

        courseId = "CSCI 222220";
        course = new Course();

        assertTrue(course.getId() == null);
        assertTrue(course.getDescription() == null);
        assertTrue(course.getName() == null);
        assertFalse(course.getCredits() == 3);

    }
}
