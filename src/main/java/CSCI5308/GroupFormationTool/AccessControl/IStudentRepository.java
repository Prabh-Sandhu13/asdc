package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.StudentCSV;

import java.util.List;
import java.util.Map;

public interface IStudentRepository {

    Map<Integer, List<StudentCSV>> createStudent(List<StudentCSV> student, String courseId);
}
