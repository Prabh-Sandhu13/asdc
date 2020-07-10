package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;
import org.apache.commons.text.RandomStringGenerator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository implements IStudentRepository {

    private IPasswordEncryptor encryptor;

    private IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();

    @Override
    public Map<Integer, List<StudentCSV>> createStudent(List<StudentCSV> studentCSVList, String courseId) {
        List<StudentCSV> newStudents = new ArrayList<StudentCSV>();
        List<StudentCSV> oldStudents = new ArrayList<StudentCSV>();
        encryptor = Injector.instance().getPasswordEncryptor();
        StoredProcedure storedProcedure = null;
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45).build();
        Map<Integer, List<StudentCSV>> studentLists = new HashMap<Integer, List<StudentCSV>>();

        for (StudentCSV studentCSV : studentCSVList) {
            try {
                storedProcedure = databaseAbstractFactory.
                        createStoredProcedureInstance("sp_createStudentFromCSV(?,?,?,?,?,?,?)");
                storedProcedure.setInputStringParameter(1, studentCSV.getBannerId());
                storedProcedure.setInputStringParameter(2, studentCSV.getFirstName());
                storedProcedure.setInputStringParameter(3, studentCSV.getLastName());
                storedProcedure.setInputStringParameter(4, studentCSV.getEmail());
                String password = pwdGenerator.generate(10);
                studentCSV.setPassword(password);
                storedProcedure.setInputStringParameter(5, encryptor.encoder(password));
                storedProcedure.setInputStringParameter(6, courseId);
                storedProcedure.registerOutputParameterBoolean(7);
                storedProcedure.execute();
                Boolean studentStatus = storedProcedure.getParameter(7);

                if (studentStatus) {
                    newStudents.add(studentCSV);
                } else {
                    oldStudents.add(studentCSV);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (storedProcedure != null) {
                    storedProcedure.removeConnections();
                }
            }

        }
        studentLists.put(DomainConstants.newStudents, newStudents);
        studentLists.put(DomainConstants.oldStudents, oldStudents);

        return studentLists;
    }

}
