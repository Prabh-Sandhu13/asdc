package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Course.StudentCSV;
import CSCI5308.GroupFormationTool.Course.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class StudentRepositoryTest {
	
    @InjectMocks
    public StudentRepository studentRepository = new StudentRepository();

    @Test
    public void createStudentTest() {

        List<StudentCSV> studentList = new ArrayList<StudentCSV>();
        StudentCSV student = new StudentCSV();
        student.setBannerId("B00827531");
        student.setEmail("test@gmail.com");
        student.setFirstName("Test");
        student.setLastName("Test");
        student.setPassword("password");
        studentList.add(student);

        student = new StudentCSV();
        student.setBannerId("B00999999");
        student.setEmail("test_2@gmail.com");
        student.setFirstName("Test_2");
        student.setLastName("Test_2");
        student.setPassword("password");
        studentList.add(student);

        assertTrue(studentList.get(0).getBannerId().length() < 256);
        assertTrue(studentList.get(0).getFirstName().equals("Test"));
        assertTrue(studentList.get(0).getLastName().length() == 4);
        assertTrue(studentList.get(0).getPassword().length() < 256);
        
        assertFalse(studentList.get(0).getBannerId().isEmpty());
        assertFalse(studentList.get(0).getEmail().length() == 0);
        assertFalse(studentList.get(0).getFirstName().isEmpty());
        assertFalse(studentList.get(0).getLastName().length() > 256);
        assertFalse(studentList.get(0).getPassword().length() > 256);
        
        assertTrue(studentList.size() == 2);
        assertFalse(studentList.isEmpty());
        
    }

}
