package CSCI5308.GroupFormationTool.Course;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IStudentCSV {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getBannerId();

    void setBannerId(String bannerId);

    String getPassword();

    void setPassword(String password);

	Map<Integer, List<StudentCSV>> createStudent(MultipartFile file, String courseId);
}
