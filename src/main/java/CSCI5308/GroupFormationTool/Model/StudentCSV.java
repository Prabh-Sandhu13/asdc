package CSCI5308.GroupFormationTool.Model;

import CSCI5308.GroupFormationTool.AccessControl.IStudentCSV;
import com.opencsv.bean.CsvBindByName;

public class StudentCSV implements IStudentCSV {

    @CsvBindByName
    private String firstName;

    @CsvBindByName
    private String lastName;

    @CsvBindByName
    private String email;

    @CsvBindByName
    private String bannerId;
    private String password;

    public StudentCSV() {
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.bannerId = null;
        this.password = null;
    }

    public StudentCSV(String firstName, String lastName, String email, String bannerId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bannerId = bannerId;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
