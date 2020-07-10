package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Mail.IMailManager;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.Reader;
import java.util.List;
import java.util.Map;

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

    private IStudentRepository studentRepository;

    private IMailManager mailManager;

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

    @Override
    public Map<Integer, List<StudentCSV>> createStudent(MultipartFile file, String courseId) {
        studentRepository = Injector.instance().getStudentRepository();
        mailManager = Injector.instance().getMailManager();
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        List<StudentCSV> badData = courseAbstractFactory.createStudentCSVListInstance();
        List<StudentCSV> properData = courseAbstractFactory.createStudentCSVListInstance();
        Map<Integer, List<StudentCSV>> studentLists = null;

        try (Reader reader = courseAbstractFactory.createBufferedReaderInstance(
                (courseAbstractFactory.createInputStreamInstance(file.getInputStream())))) {

            CsvToBean<StudentCSV> csvToBean = courseAbstractFactory.createCsvToBeanBuilderInstance(reader);

            List<StudentCSV> students = csvToBean.parse();
            for (StudentCSV studentCSV : students) {
                if (checkForBadData(studentCSV)) {
                    badData.add(studentCSV);
                } else {
                    properData.add(studentCSV);
                }
            }
            studentLists = studentRepository.createStudent(properData, courseId);
            if (studentLists != null && studentLists.size() > 0) {
                studentLists.put(DomainConstants.badData, badData);
                mailManager.sendBatchMail(studentLists.get(DomainConstants.newStudents), courseId);
            }

        } catch (Exception ex) {
            return null;
        }
        return studentLists;
    }

    private boolean checkForBadData(StudentCSV studentCSV) {
        return studentCSV.getBannerId() == null || studentCSV.getFirstName() == null
                || studentCSV.getLastName() == null || studentCSV.getEmail() == null
                || studentCSV.getBannerId().equals("") || studentCSV.getFirstName().equals("")
                || studentCSV.getLastName().equals("") || studentCSV.getEmail().equals("");
    }

}
