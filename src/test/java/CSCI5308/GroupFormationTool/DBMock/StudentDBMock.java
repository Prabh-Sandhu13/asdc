package CSCI5308.GroupFormationTool.DBMock;

import CSCI5308.GroupFormationTool.AccessControl.IStudentCSV;
import CSCI5308.GroupFormationTool.AccessControl.IStudentRepository;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDBMock implements IStudentRepository {

    private String firstName;

    private String lastName;

    private String email;

    private String bannerId;

    private String password;

    public StudentDBMock() {
        firstName = "Tanu";
        lastName = "Gulia";
        email = "tn300318@dal.ca";
        bannerId = "B00839890";
        password = "password1234";
    }

    @Override
    public Map<Integer, List<StudentCSV>> createStudent(List<StudentCSV> student, String courseId) {

        StudentCSV newUsers = new StudentCSV();
        List<StudentCSV> oldUsersList = new ArrayList<>();
        List<StudentCSV> newUsersList = new ArrayList<>();

        newUsers.setFirstName(firstName);
        newUsers.setLastName(lastName);
        newUsers.setEmail(email);
        newUsers.setBannerId(bannerId);
        newUsers.setPassword(password);
        newUsersList.add(newUsers);

        StudentCSV oldUsers = new StudentCSV();
        oldUsers.setFirstName("Padmesh");
        oldUsers.setLastName("Donthu");
        oldUsers.setEmail("padmeshdonth@gmail.com");
        oldUsers.setBannerId("B00854462");
        oldUsers.setPassword("testsample");
        oldUsersList.add(oldUsers);

        Map<Integer, List<StudentCSV>> map = new HashMap<>();
        map.put(0, newUsersList);
        map.put(1, oldUsersList);
        return map;
    }

    public IStudentCSV loadStudentCSVWithID(IStudentCSV studentCSV) {
        studentCSV.setBannerId(bannerId);
        studentCSV.setEmail(email);
        studentCSV.setFirstName(firstName);
        studentCSV.setLastName(lastName);
        studentCSV.setPassword(password);
        return studentCSV;
    }

}
