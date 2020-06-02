package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

import CSCI5308.GroupFormationTool.Model.StudentCSV;

public interface IStudentRepository {

	public boolean createStudent(List<StudentCSV> student);
}
