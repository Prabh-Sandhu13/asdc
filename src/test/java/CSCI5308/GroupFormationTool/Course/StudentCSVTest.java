package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Course.IStudentCSV;
import CSCI5308.GroupFormationTool.Course.StudentCSV;
import CSCI5308.GroupFormationTool.Course.StudentDBMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class StudentCSVTest {

    public StudentCSV studentCSV;
    public StudentRepository studentRepository;

    @BeforeEach
    public void init() {
        studentCSV = new StudentCSV();
        studentRepository = mock(StudentRepository.class);
        Injector.instance().setStudentRepository(studentRepository);
    }
	
    private IStudentCSV createDefaultStudentCSV() {
        StudentDBMock studentDBMock = new StudentDBMock();
        IStudentCSV studentCSV = loadStudentCSV(studentDBMock);
        return studentCSV;
    }

    private IStudentCSV loadStudentCSV(StudentDBMock studentDBMock) {
        IStudentCSV studentCSV = new StudentCSV();
        studentCSV = studentDBMock.loadStudentCSVWithID(studentCSV);
        return studentCSV;
    }

    @Test
    public void getFirstNameTest() {
        IStudentCSV studentCSV = createDefaultStudentCSV();
        assertEquals("Tanu", studentCSV.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        IStudentCSV studentCSV = new StudentCSV();
        studentCSV.setFirstName("Padmesh");
        assertEquals("Padmesh", studentCSV.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        IStudentCSV studentCSV = createDefaultStudentCSV();
        assertEquals("Gulia", studentCSV.getLastName());
    }

    @Test
    public void setLastNameTest() {
        IStudentCSV studentCSV = new StudentCSV();
        studentCSV.setLastName("Donthu");
        assertEquals("Donthu", studentCSV.getLastName());
    }

    @Test
    public void getBannerIdTest() {
        IStudentCSV studentCSV = createDefaultStudentCSV();
        assertEquals("B00839890", studentCSV.getBannerId());
    }

    @Test
    public void setBannerIdTest() {
        IStudentCSV studentCSV = new StudentCSV();
        studentCSV.setBannerId("B0000000");
        assertEquals("B0000000", studentCSV.getBannerId());
    }

    @Test
    public void getEmailIdTest() {
        IStudentCSV studentCSV = createDefaultStudentCSV();
        assertEquals("tn300318@dal.ca", studentCSV.getEmail());
    }

    @Test
    public void setEmailIdTest() {
        IStudentCSV studentCSV = new StudentCSV();
        studentCSV.setEmail("padmeshdonthu@gmail.com");
        assertEquals("padmeshdonthu@gmail.com", studentCSV.getEmail());
    }

    @Test
    public void getPasswordTest() {
        IStudentCSV studentCSV = createDefaultStudentCSV();
        assertEquals("password1234", studentCSV.getPassword());
    }

    @Test
    public void setPasswordTest() {
        IStudentCSV studentCSV = new StudentCSV();
        studentCSV.setPassword("password");
        assertEquals("password", studentCSV.getPassword());
    }

    @Test
    public void StudentCSVParameterizedTest() {
        IStudentCSV studentCSV = new StudentCSV("Padmesh",
                "Donthu", "padmeshdonthu@gmail.com",
                "B00854462", "sample123");

        assertEquals("Padmesh", studentCSV.getFirstName());
        assertFalse(studentCSV.getLastName() == null);
        assertTrue(studentCSV.getEmail().equals("padmeshdonthu@gmail.com"));
        assertFalse(studentCSV.getPassword().isEmpty());
        assertTrue(studentCSV.getPassword().length() < 200);
        assertFalse(studentCSV.getBannerId().isEmpty());
        assertTrue(studentCSV.getBannerId().length() < 100);
    }

    @Test
    void createStudent() {
        String data = "firstName,lastName,email,bannerId\n";
        data += "padmesh,donthu,padmeshdonthu@gmail.com,B00854462";
        String finalData = data;
        MultipartFile multipartFile = new MultipartFile() {

            @Override
            public String getName() {
                return "sample";
            }

            @Override
            public String getOriginalFilename() {
                return "sample";
            }

            @Override
            public String getContentType() {
                return MediaType.TEXT_PLAIN_VALUE;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return finalData.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return finalData.getBytes();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                InputStream inputStream = new ByteArrayInputStream(finalData.getBytes());
                return inputStream;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                // not reqd
            }
        };

        String courseId = "CSCI 5308";
        ArrayList<StudentCSV> studentCSVs = new ArrayList<>();
        HashMap<Integer, List<StudentCSV>> studentLists = new HashMap<>();
        when(studentRepository.createStudent(studentCSVs, courseId)).thenReturn(studentLists);
        assertTrue(studentCSV.createStudent(multipartFile, courseId).size() == 0);
    }


}
