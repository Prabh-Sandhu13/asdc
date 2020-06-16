package CSCI5308.GroupFormationTool.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.RandomStringGenerator;

import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
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

		for (StudentCSV studentRow : student) {
			if (studentRow.getBannerId() == null || studentRow.getFirstName() == null
					|| studentRow.getLastName() == null || studentRow.getEmail() == null
					|| studentRow.getBannerId().equals("") || studentRow.getFirstName().equals("")
					|| studentRow.getLastName().equals("") || studentRow.getEmail().equals("")) {
				badData.add(studentRow);
			} else {
				try {
					storedProcedure = new StoredProcedure("sp_createStudentFromCSV(?,?,?,?,?,?,?)");
					storedProcedure.setInputStringParameter(1, studentRow.getBannerId());
					storedProcedure.setInputStringParameter(2, studentRow.getFirstName());
					storedProcedure.setInputStringParameter(3, studentRow.getLastName());
					storedProcedure.setInputStringParameter(4, studentRow.getEmail());
					String password = pwdGenerator.generate(10);
					studentRow.setPassword(password);
					storedProcedure.setInputStringParameter(5, encryptor.encoder(password));
					storedProcedure.setInputStringParameter(6, courseId);
					storedProcedure.registerOutputParameterBoolean(7);
					storedProcedure.execute();
					Boolean studentStatus = storedProcedure.getParameter(7);

					if (studentStatus) {
						newStudents.add(studentRow);
					} else {
						oldStudents.add(studentRow);
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

		studentLists.put(DomainConstants.newStudents, newStudents);
		studentLists.put(DomainConstants.oldStudents, oldStudents);
		studentLists.put(DomainConstants.badData, badData);

		return studentLists;
	}
}
