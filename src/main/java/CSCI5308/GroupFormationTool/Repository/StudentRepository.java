package CSCI5308.GroupFormationTool.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.RandomStringGenerator;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Database.ConnectionManager;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.AccessControl.*;

public class StudentRepository implements IStudentRepository {

	private IPasswordEncryptor encryptor;

	@Override
	public Map<Integer, List<StudentCSV>> createStudent(List<StudentCSV> student, String courseId) {
		List<StudentCSV> newStudents = new ArrayList<StudentCSV>();
		List<StudentCSV> oldStudents = new ArrayList<StudentCSV>();
		List<StudentCSV> badData = new ArrayList<StudentCSV>();
		encryptor = Injector.instance().getPasswordEncryptor();
		StoredProcedure storedProcedure = null;

		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();

		Map<Integer, List<StudentCSV>> studentLists = new HashMap<Integer, List<StudentCSV>>();

		for (StudentCSV stu : student) {
			if (stu.getBannerId() == null || stu.getFirstName() == null || stu.getLastName() == null
					|| stu.getEmail() == null || stu.getBannerId().equals("") || stu.getFirstName().equals("")
					|| stu.getLastName().equals("") || stu.getEmail().equals("")) {
				badData.add(stu);
			} else {
				try {
					storedProcedure = new StoredProcedure("sp_createStudentFromCSV(?,?,?,?,?,?,?)");
					storedProcedure.setInputStringParameter(1, stu.getBannerId());
					storedProcedure.setInputStringParameter(2, stu.getFirstName());
					storedProcedure.setInputStringParameter(3, stu.getLastName());
					storedProcedure.setInputStringParameter(4, stu.getEmail());
					String password = pwdGenerator.generate(10);
					stu.setPassword(password);
					storedProcedure.setInputStringParameter(5, encryptor.encoder(password));
					storedProcedure.setInputStringParameter(6, courseId);
					storedProcedure.registerOutputParameterBoolean(7);
					storedProcedure.execute();
					Boolean studentStatus = storedProcedure.getParameter(7);

					if (studentStatus) {
						newStudents.add(stu);
					} else {
						oldStudents.add(stu);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					if (storedProcedure != null) {
						storedProcedure.removeConnections();
					}
				}

			}
		}

		studentLists.put(0, newStudents);
		studentLists.put(1, oldStudents);
		studentLists.put(2, badData);

		return studentLists;
	}
}
