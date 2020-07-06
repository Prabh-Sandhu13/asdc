package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    public StudentService studentService;
    public StudentRepository studentRepository;

    @BeforeEach
    public void init() {
        studentService = mock(StudentService.class);
        studentRepository = mock(StudentRepository.class);
        Injector.instance().setStudentRepository(studentRepository);
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
        assertTrue(studentService.createStudent(multipartFile, courseId).size() == 0);
    }
}
