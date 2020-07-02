package CSCI5308.GroupFormationTool.Course;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IStudentService {
    Map<Integer, List<StudentCSV>> createStudent(MultipartFile file, String courseId);
}
