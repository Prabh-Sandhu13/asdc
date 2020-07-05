package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Mail.IMailManager;
import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentService implements IStudentService {

    private IStudentRepository studentRepository;
    private IMailManager mailManager;

    @Override
    public Map<Integer, List<StudentCSV>> createStudent(MultipartFile file, String courseId) {
        studentRepository = Injector.instance().getStudentRepository();
        mailManager = Injector.instance().getMailManager();

        List<StudentCSV> badData = new ArrayList<StudentCSV>();
        List<StudentCSV> properData = new ArrayList<StudentCSV>();
        Map<Integer, List<StudentCSV>> studentLists = null;

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<StudentCSV> csvToBean = new CsvToBeanBuilder<StudentCSV>(reader).withType(StudentCSV.class)
                    .withIgnoreLeadingWhiteSpace(true).build();

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
        if (studentCSV.getBannerId() == null || studentCSV.getFirstName() == null
                || studentCSV.getLastName() == null || studentCSV.getEmail() == null
                || studentCSV.getBannerId().equals("") || studentCSV.getFirstName().equals("")
                || studentCSV.getLastName().equals("") || studentCSV.getEmail().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
